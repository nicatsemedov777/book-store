package az.ingress.bookstore.service;

import az.ingress.bookstore.dto.request.AccountSignUpRequest;
import az.ingress.bookstore.dto.response.BookResponse;
import az.ingress.bookstore.entity.Account;
import org.springframework.http.HttpStatus;

import java.security.Principal;
import java.util.List;

public interface StudentService {
    void addStudent(AccountSignUpRequest accountSignUpRequest, Account account);

    BookResponse readBook(String bookId, Principal principal);
    List<BookResponse> getAllBooksByStudentId(Principal principal);

}
