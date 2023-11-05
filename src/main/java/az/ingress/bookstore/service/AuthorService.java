package az.ingress.bookstore.service;

import az.ingress.bookstore.dto.request.AccountSignUpRequest;
import az.ingress.bookstore.dto.response.BookResponse;
import az.ingress.bookstore.entity.Account;
import org.springframework.http.HttpStatus;

import java.security.Principal;
import java.util.List;

public interface AuthorService {
    void addAuthor(AccountSignUpRequest accountSignUpRequest, Account account);
    List<BookResponse> getBooks(Principal principal);

    void deleteBook(String bookId,Principal principal);
}
