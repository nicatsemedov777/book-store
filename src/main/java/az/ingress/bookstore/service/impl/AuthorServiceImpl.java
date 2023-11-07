package az.ingress.bookstore.service.impl;

import az.ingress.bookstore.converter.BookResponseConverter;
import az.ingress.bookstore.dto.request.AccountSignUpRequest;
import az.ingress.bookstore.dto.response.BookResponse;
import az.ingress.bookstore.entity.Account;
import az.ingress.bookstore.entity.Author;
import az.ingress.bookstore.entity.Book;
import az.ingress.bookstore.error.exception.ResourceNotFoundException;
import az.ingress.bookstore.repository.AccountRepository;
import az.ingress.bookstore.repository.AuthorRepository;
import az.ingress.bookstore.repository.BookRepository;
import az.ingress.bookstore.repository.StudentBookEnrollmentRepository;
import az.ingress.bookstore.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final AccountRepository accountRepository;
    private final BookRepository bookRepository;
    private final StudentBookEnrollmentRepository enrollmentRepository;
    private final BookResponseConverter bookResponseConverter;

    @Override
    public void addAuthor(AccountSignUpRequest accountSignUpRequest, Account account) {
        Author author = buildAuthor(accountSignUpRequest, account);
        authorRepository.save(author);
    }


    @Override
    public List<BookResponse> getBooks(Principal principal) {
        return authorRepository.getAllBookByAccountId(principal.getName())
                .stream()
                .map(bookResponseConverter)
                .collect(Collectors.toList());
    }

    private static Author buildAuthor(AccountSignUpRequest accountSignUpRequest, Account account) {
        return Author.builder()
                .name(accountSignUpRequest.getName())
                .age(accountSignUpRequest.getAge())
                .account(account)
                .build();
    }
}
