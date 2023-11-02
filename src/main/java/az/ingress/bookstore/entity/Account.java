package az.ingress.bookstore.entity;

import az.ingress.bookstore.entity.generator.IdGenerator;
import az.ingress.bookstore.util.DateHelper;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

@Entity
@Table(name = "accounts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {
    @Id
    @GeneratedValue(generator = "id-generator")
    @GenericGenerator(name = "id-generator", type = IdGenerator.class)
    private String id;

    private String email;
    private String password;

    @ManyToOne
    private Role role;

    @Column(name = "create_date")
    @Builder.Default
    private Date createDate = DateHelper.now();
}

