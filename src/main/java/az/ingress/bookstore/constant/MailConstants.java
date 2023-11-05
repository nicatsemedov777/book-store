package az.ingress.bookstore.constant;

import jakarta.validation.Valid;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class MailConstants {
    @Value("${spring.mail.username}")
    private String email;
}
