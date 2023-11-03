package az.ingress.bookstore.service;

import az.ingress.bookstore.dto.request.AccountSignUpRequest;
import az.ingress.bookstore.entity.Account;

public interface AuthorService {
    void addAuthor(AccountSignUpRequest accountSignUpRequest, Account account);
}
