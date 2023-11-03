package az.ingress.bookstore.repository;

import az.ingress.bookstore.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author,String> {
    Optional<Author> findByAccount_Id(String accountId);
}
