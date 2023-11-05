package az.ingress.bookstore.service.impl;

import az.ingress.bookstore.converter.BookResponseConverter;
import az.ingress.bookstore.converter.StudentResponseConverter;
import az.ingress.bookstore.dto.request.BookCreateRequest;
import az.ingress.bookstore.dto.response.StudentResponse;
import az.ingress.bookstore.entity.Account;
import az.ingress.bookstore.entity.Book;
import az.ingress.bookstore.error.exception.ResourceNotFoundException;
import az.ingress.bookstore.repository.*;
import az.ingress.bookstore.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    private final StudentRepository studentRepository;
    private final StudentBookEnrollmentRepository enrollmentRepository;
    private final StudentResponseConverter studentResponseConverter;
    private final BookResponseConverter bookResponseConverter;

    @Override
    public HttpStatus createBook(BookCreateRequest bookCreateRequest, Principal principal) {
        Account account = accountRepository.findById(principal.getName()).orElseThrow(ResourceNotFoundException::new);
        var author = authorRepository.findByAccountId(account.getId()).orElseThrow(ResourceNotFoundException::new);
        Book book = Book.builder()
                .name(bookCreateRequest.getName())
                .author(author)
                .build();
        bookRepository.save(book);
        return HttpStatus.OK;
    }

    @Override
    public List<StudentResponse> getReadersByBookId(String bookId) {
        return enrollmentRepository.findAllStudentByBookId(bookId)
                .stream()
                .map(studentResponseConverter)
                .collect(Collectors.toList());
    }
}
