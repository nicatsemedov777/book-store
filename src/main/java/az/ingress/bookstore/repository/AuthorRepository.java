package az.ingress.bookstore.repository;

import az.ingress.bookstore.entity.Author;
import az.ingress.bookstore.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author,String> {
    Optional<Author> findByAccountId(String accountId);

    @Query("select b from Book b join Author a on a.id = b.author.id where a.account.id = :accountId")
    List<Book> getAllBookByAccountId(String accountId);
}
