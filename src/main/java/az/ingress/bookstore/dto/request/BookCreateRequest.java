package az.ingress.bookstore.dto.request;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookCreateRequest {
    @NotEmpty(message = "Book's name is required")
    private String name;

}