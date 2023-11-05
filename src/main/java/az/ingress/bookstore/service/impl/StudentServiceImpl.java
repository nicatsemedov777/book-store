package az.ingress.bookstore.service.impl;

import az.ingress.bookstore.converter.BookResponseConverter;
import az.ingress.bookstore.dto.request.AccountSignUpRequest;
import az.ingress.bookstore.dto.response.BookResponse;
import az.ingress.bookstore.entity.Account;
import az.ingress.bookstore.entity.Book;
import az.ingress.bookstore.entity.Student;
import az.ingress.bookstore.entity.StudentBookEnrollment;
import az.ingress.bookstore.error.exception.ResourceNotFoundException;
import az.ingress.bookstore.repository.BookRepository;
import az.ingress.bookstore.repository.StudentBookEnrollmentRepository;
import az.ingress.bookstore.repository.StudentRepository;
import az.ingress.bookstore.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final BookRepository bookRepository;
    private final StudentBookEnrollmentRepository enrollmentRepository;
    private final BookResponseConverter bookResponseConverter;

    @Override
    public void addStudent(AccountSignUpRequest accountSignUpRequest, Account account) {
        Student student = buildStudent(accountSignUpRequest, account);
        studentRepository.save(student);
    }

    @Override
    public BookResponse readBook(String bookId, Principal principal) {
        var book = bookRepository.findById(bookId).orElseThrow(()
                -> new ResourceNotFoundException("Book not found with this id:" + bookId));

        var student = studentRepository.findByAccountId(principal.getName()).orElseThrow(()
                -> new ResourceNotFoundException("Student not found with this id:" + principal.getName()));

        saveStudentBookEnrollment(book, student);
        return bookResponseConverter.apply(book);
    }

    private void saveStudentBookEnrollment(Book book, Student student) {
        enrollmentRepository.save(StudentBookEnrollment.builder().
                book(book).
                student(student).
                build());
    }

    @Override
    public List<BookResponse> getAllBooksByStudentId(Principal principal) {
        return enrollmentRepository.findAllBooksByAccountId(principal.getName())
                .stream()
                .map(bookResponseConverter)
                .collect(Collectors.toList());
    }
    private static Student buildStudent(AccountSignUpRequest accountSignUpRequest, Account account) {
        return Student.builder()
                .name(accountSignUpRequest.getName())
                .age(accountSignUpRequest.getAge())
                .account(account)
                .build();
    }
}
