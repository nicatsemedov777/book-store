package az.ingress.bookstore.repository;

import az.ingress.bookstore.entity.Book;
import az.ingress.bookstore.entity.Student;
import az.ingress.bookstore.entity.StudentBookEnrollment;
import az.ingress.bookstore.repository.projection.StudentEmailProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface StudentBookEnrollmentRepository extends JpaRepository<StudentBookEnrollment,String> {
    @Query("SELECT s from Student s JOIN StudentBookEnrollment sbe on sbe.student.id = s.id  WHERE sbe.book.id = ?1")
    List<Student> findAllStudentByBookId(String bookId);

    @Query("Select b from Book b join  StudentBookEnrollment sbe on sbe.book.id = b.id where sbe.student.id = ?1")
    List<Book> findAllBookByStudentId(String studentId);
    @Transactional
    @Modifying
    @Query("delete from StudentBookEnrollment s where s.book.id = :bookId")
    void deleteByBookId(String bookId);

    @Query("select sbe.student.account.email as email from StudentBookEnrollment sbe where sbe.book.author.id = ?1")
    List<StudentEmailProjection> getStudentByAuthorId(String authorId);
}
