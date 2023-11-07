package az.ingress.bookstore.service.impl;

import az.ingress.bookstore.converter.BookResponseConverter;
import az.ingress.bookstore.converter.StudentResponseConverter;
import az.ingress.bookstore.dto.request.BookCreateRequest;
import az.ingress.bookstore.dto.response.BookResponse;
import az.ingress.bookstore.dto.response.StudentResponse;
import az.ingress.bookstore.entity.Account;
import az.ingress.bookstore.entity.Author;
import az.ingress.bookstore.entity.Book;
import az.ingress.bookstore.error.exception.ResourceNotFoundException;
import az.ingress.bookstore.repository.AccountRepository;
import az.ingress.bookstore.repository.AuthorRepository;
import az.ingress.bookstore.repository.BookRepository;
import az.ingress.bookstore.repository.StudentBookEnrollmentRepository;
import az.ingress.bookstore.repository.projection.StudentEmailProjection;
import az.ingress.bookstore.service.BookService;
import az.ingress.bookstore.service.MailService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final AccountRepository accountRepository;
    private final StudentBookEnrollmentRepository enrollmentRepository;
    private final StudentResponseConverter studentResponseConverter;
    private final MailService mailService;
    private final BookResponseConverter bookResponseConverter;

    @Override
    public BookResponse createBook(BookCreateRequest bookCreateRequest, Principal principal) {
        Account account = accountRepository.findById(principal.getName()).orElseThrow(ResourceNotFoundException::new);
        var author = authorRepository.findByAccountId(account.getId()).orElseThrow(ResourceNotFoundException::new);
        Book book = Book.builder()
                .name(bookCreateRequest.getName())
                .author(author)
                .build();
        bookRepository.save(book);

        var studentEmails = enrollmentRepository.getStudentByAuthorId(author.getId());

        if (CollectionUtils.isNotEmpty(studentEmails)) {
            var studentEmailArray = convertListToArray(studentEmails);
            mailService.sendNewBookNotification(studentEmailArray,
                    String.format("Author %s published new book: %s.", author.getName(), book.getName()));
        }

        return bookResponseConverter.apply(book);
    }

    private String[] convertListToArray(List<StudentEmailProjection> studentEmails) {
        String[] arr = new String[studentEmails.size()];
        for (int i = 0; i < studentEmails.size(); i++) {
            arr[i] = studentEmails.get(i).getEmail();
        }
        return arr;
    }

    @Override
    public List<StudentResponse> getReadersByBookId(String bookId) {
        return enrollmentRepository.findAllStudentByBookId(bookId)
                .stream()
                .map(studentResponseConverter)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookResponse> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(bookResponseConverter)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteBook(String bookId, Principal principal) {
        Author author = authorRepository.findByAccountId(principal.getName()).orElseThrow(ResourceNotFoundException::new);
        enrollmentRepository.deleteByBookId(bookId);
        bookRepository.deleteByIdAndAuthorId(bookId, author.getId());
    }
}
