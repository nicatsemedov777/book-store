package az.ingress.bookstore.service;

import az.ingress.bookstore.dto.request.AccountSignUpRequest;
import az.ingress.bookstore.entity.Account;

public interface StudentService {
    void addStudent(AccountSignUpRequest accountSignUpRequest, Account account);

}
