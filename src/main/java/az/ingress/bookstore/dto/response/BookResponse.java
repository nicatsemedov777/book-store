package az.ingress.bookstore.dto.response;

import az.ingress.bookstore.entity.Author;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookResponse {
    public String bookId;
    public String bookName;
    public String authorId;
}
