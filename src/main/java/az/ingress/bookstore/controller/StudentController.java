package az.ingress.bookstore.controller;

import az.ingress.bookstore.dto.response.BookResponse;
import az.ingress.bookstore.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

import static az.ingress.bookstore.util.ResponseBuilder.buildResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/students")
public class StudentController {
    private final StudentService studentService;

    @PostMapping("{bookId}/read")
    @PreAuthorize("hasAuthority('STUDENT')")
    public ResponseEntity<BookResponse> readBook(@PathVariable String bookId, Principal principal) {
        return buildResponse(studentService.readBook(bookId, principal));
    }

    @GetMapping("/books")
    @PreAuthorize("hasAuthority('STUDENT')")
    public ResponseEntity<List<BookResponse>> getAllBooksByStudent(Principal principal) {
        return buildResponse(studentService.getAllBooksByStudentId(principal));
    }
}
