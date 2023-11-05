package az.ingress.bookstore.entity;

import az.ingress.bookstore.entity.generator.IdGenerator;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "students")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {
    @Id
    @GeneratedValue(generator = "id-generator")
    @GenericGenerator(name = "id-generator", type = IdGenerator.class)
    private String id;

    private String name;
    private Integer age;

    @OneToOne
    private Account account;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "student_books",
            joinColumns ={@JoinColumn(name = "student_id", referencedColumnName = "id")},
            inverseJoinColumns ={@JoinColumn(name = "book_id", referencedColumnName = "id")}
    )

    private List<Book> books = new ArrayList<>();
}
