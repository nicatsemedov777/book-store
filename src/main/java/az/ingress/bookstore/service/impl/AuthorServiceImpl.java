package az.ingress.bookstore.service.impl;

import az.ingress.bookstore.dto.request.AccountSignUpRequest;
import az.ingress.bookstore.entity.Account;
import az.ingress.bookstore.entity.Author;
import az.ingress.bookstore.repository.AuthorRepository;
import az.ingress.bookstore.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    public void addAuthor(AccountSignUpRequest accountSignUpRequest, Account account) {
        Author author = buildAuthor(accountSignUpRequest, account);
        authorRepository.save(author);
    }

    private static Author buildAuthor(AccountSignUpRequest accountSignUpRequest, Account account) {
        return Author.builder()
                .name(accountSignUpRequest.getName())
                .age(accountSignUpRequest.getAge())
                .account(account)
                .build();
    }
}
