package az.ingress.bookstore.repository;

import az.ingress.bookstore.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book,String> {
    @Transactional
    @Modifying
    @Query(value = "DELETE from books b where b.id = ?1 AND b.author_id = ?2", nativeQuery = true)
    void deleteByIdAndAuthorId(String id, String authorId);


    @Query("SELECT b from Book b where b.id = :bookId")
    Optional<List<Book>> findAllById(String bookId);
}
