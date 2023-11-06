package az.ingress.bookstore.entity;

import az.ingress.bookstore.entity.generator.IdGenerator;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "authors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Author {
    @Id
    @GeneratedValue(generator = "id-generator")
    @GenericGenerator(name = "id-generator", type = IdGenerator.class)
    private String id;

    private String name;
    private Integer age;

    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @OneToMany
    private List<Book> books = new ArrayList<>();
}
