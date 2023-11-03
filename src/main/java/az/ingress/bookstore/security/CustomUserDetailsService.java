package az.ingress.bookstore.security;

import az.ingress.bookstore.entity.Account;
import az.ingress.bookstore.error.exception.AuthenticationException;
import az.ingress.bookstore.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final AccountRepository accountRepository;
    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        Account account = accountRepository.findById(id)
                .orElseThrow(()->new AuthenticationException("Bad credentials"));
        return  new User(account.getId(), "", List.of(new SimpleGrantedAuthority(account.getRole().getName())));
    }
}
