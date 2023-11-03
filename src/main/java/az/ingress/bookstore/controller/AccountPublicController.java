package az.ingress.bookstore.controller;

import az.ingress.bookstore.dto.request.AccountSignInRequest;
import az.ingress.bookstore.dto.request.AccountSignUpRequest;
import az.ingress.bookstore.model.jwt.JwtToken;
import az.ingress.bookstore.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static az.ingress.bookstore.util.ResponseBuilder.buildResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/public/accounts")
public class AccountPublicController {
    private final AccountService accountService;

    @PostMapping("/sign-up")
    public ResponseEntity<JwtToken> signUp(@RequestBody @Valid AccountSignUpRequest accountSignUpRequest) {
        return buildResponse(accountService.signUp(accountSignUpRequest));
    }
    @PostMapping("/sign-in")
    public ResponseEntity<JwtToken> signIn(@RequestBody @Valid AccountSignInRequest accountSignInRequest) {
        return buildResponse(accountService.signIn(accountSignInRequest));
    }
}
