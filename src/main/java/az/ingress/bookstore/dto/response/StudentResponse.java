package az.ingress.bookstore.dto.response;

import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentResponse {
    private String id;
    private String name;
    private Integer age;
}
