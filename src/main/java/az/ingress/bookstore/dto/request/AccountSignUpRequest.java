package az.ingress.bookstore.dto.request;

import az.ingress.bookstore.enums.Profession;
import az.ingress.bookstore.validator.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountSignUpRequest {
    @NotEmpty(message = "Name is required.")
    private String name;
    @NotNull(message = "Age can't be null.")
    private Integer age;

    @Email
    private String email;

    @NotEmpty(message = "Password is required.")
    @Size(min = 8, max = 20,message = "Password's length must be longer 8 and less than 20")
    private String password;

    @NotNull(message = "Profession is required.")
    private Profession profession;
}
