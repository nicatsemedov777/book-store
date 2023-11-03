package az.ingress.bookstore.dto.request;

import az.ingress.bookstore.validator.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountSignInRequest {
    @Email
    private String email;
    @NotEmpty(message = "Password is required.")
    private String password;
}
