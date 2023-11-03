package az.ingress.bookstore.service.impl;

import az.ingress.bookstore.dto.request.AccountSignInRequest;
import az.ingress.bookstore.dto.request.AccountSignUpRequest;
import az.ingress.bookstore.entity.Account;
import az.ingress.bookstore.entity.Role;
import az.ingress.bookstore.error.exception.AuthenticationException;
import az.ingress.bookstore.error.exception.ResourceAlreadyExistException;
import az.ingress.bookstore.error.exception.ResourceNotFoundException;
import az.ingress.bookstore.model.jwt.JwtToken;
import az.ingress.bookstore.repository.AccountRepository;
import az.ingress.bookstore.repository.RoleRepository;
import az.ingress.bookstore.security.JWTProvider;
import az.ingress.bookstore.service.AccountService;
import az.ingress.bookstore.service.AuthorService;
import az.ingress.bookstore.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final StudentService studentService;
    private final AuthorService authorService;
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final JWTProvider jwtProvider;

    @Override
    public JwtToken signUp(AccountSignUpRequest accountSignUpRequest) {
        var isExist = accountRepository.existsByEmail(accountSignUpRequest.getEmail());
        if (isExist) {
            throw new ResourceAlreadyExistException("Account already exists");
        }

        var role = roleRepository.findByName(accountSignUpRequest.getProfession().name());
        Account account = buildAccount(accountSignUpRequest, role);
        accountRepository.save(account);

        switch (accountSignUpRequest.getProfession()) {
            case AUTHOR -> authorService.addAuthor(accountSignUpRequest, account);
            case STUDENT -> studentService.addStudent(accountSignUpRequest, account);
        }

        return jwtProvider.getJWTToken(account.getId());
    }

    @Override
    public JwtToken signIn(AccountSignInRequest accountSignInRequest) {
        Account account = accountRepository.findByEmail(accountSignInRequest.getEmail()).orElseThrow(()
                -> new ResourceNotFoundException("Account not found with this email."));

        if (passwordEncoder.matches(accountSignInRequest.getPassword(), account.getPassword()))
            return jwtProvider.getJWTToken(account.getId());

        throw new AuthenticationException("Bad credentials");
    }

    private Account buildAccount(AccountSignUpRequest accountSignUpRequest, Role role) {
        return Account.builder()
                .email(accountSignUpRequest.getEmail())
                .password(passwordEncoder.encode(accountSignUpRequest.getPassword()))
                .role(role)
                .build();
    }
}
