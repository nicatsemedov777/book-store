package az.ingress.bookstore.controller;

import az.ingress.bookstore.dto.request.BookCreateRequest;
import az.ingress.bookstore.dto.response.BookResponse;
import az.ingress.bookstore.dto.response.StudentResponse;
import az.ingress.bookstore.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

import static az.ingress.bookstore.util.ResponseBuilder.buildResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/books")
public class BookController {
    private final BookService bookService;

    @PostMapping
    @PreAuthorize("hasAuthority('AUTHOR')")
    public ResponseEntity<BookResponse> createBook(@RequestBody @Valid BookCreateRequest bookCreateRequest, Principal principal) {
        return buildResponse(bookService.createBook(bookCreateRequest, principal));
    }

    @GetMapping("{bookId}/readers")
    public ResponseEntity<List<StudentResponse>> getReadersByBookId(@PathVariable String bookId) {
        return buildResponse(bookService.getReadersByBookId(bookId));
    }

    @GetMapping
    public ResponseEntity<List<BookResponse>> getAllBooks() {
        return buildResponse(bookService.getAllBooks());
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('AUTHOR')")
    public ResponseEntity<HttpStatus> deleteBook(@PathVariable(name = "id") String bookId, Principal principal) {
        bookService.deleteBook(bookId, principal);
        return buildResponse();
    }
}
