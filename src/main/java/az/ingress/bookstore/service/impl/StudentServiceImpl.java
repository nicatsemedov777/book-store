package az.ingress.bookstore.service.impl;

import az.ingress.bookstore.dto.request.AccountSignUpRequest;
import az.ingress.bookstore.entity.Account;
import az.ingress.bookstore.entity.Student;
import az.ingress.bookstore.repository.StudentRepository;
import az.ingress.bookstore.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    @Override
    public void addStudent(AccountSignUpRequest accountSignUpRequest, Account account) {
        Student student = buildStudent(accountSignUpRequest, account);
        studentRepository.save(student);
    }

    private static Student buildStudent(AccountSignUpRequest accountSignUpRequest, Account account) {
        return Student.builder()
                .name(accountSignUpRequest.getName())
                .age(accountSignUpRequest.getAge())
                .account(account)
                .build();
    }
}
