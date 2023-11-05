package az.ingress.bookstore.converter;

import az.ingress.bookstore.dto.response.StudentResponse;
import az.ingress.bookstore.entity.Student;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class StudentResponseConverter implements Function<Student, StudentResponse> {

    @Override
    public StudentResponse apply(Student student) {
        return StudentResponse.builder()
                .id(student.getId())
                .age(student.getAge())
                .name(student.getName())
                .build();
    }
}
