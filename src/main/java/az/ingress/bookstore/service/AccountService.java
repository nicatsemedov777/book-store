package az.ingress.bookstore.service;

import az.ingress.bookstore.dto.request.AccountSignInRequest;
import az.ingress.bookstore.dto.request.AccountSignUpRequest;
import az.ingress.bookstore.model.jwt.JwtToken;

public interface AccountService {
    JwtToken signUp(AccountSignUpRequest accountSignUpRequest);

    JwtToken signIn(AccountSignInRequest accountSignInRequest);
}
