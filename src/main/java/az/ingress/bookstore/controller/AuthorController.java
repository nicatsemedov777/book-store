package az.ingress.bookstore.controller;


import az.ingress.bookstore.dto.response.BookResponse;
import az.ingress.bookstore.service.AuthorService;
import az.ingress.bookstore.service.BookService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

import static az.ingress.bookstore.util.ResponseBuilder.buildResponse;

@RestController
@RequestMapping("api/v1/authors")
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('AUTHOR')")
    public ResponseEntity<HttpStatus> deleteBook(@PathVariable(name = "id") String bookId,Principal principal) {
        authorService.deleteBook(bookId,principal);
        return buildResponse();
    }

    @GetMapping("/books")
    @PreAuthorize("hasAuthority('AUTHOR')")
    public ResponseEntity<List<BookResponse>> getBooks(Principal principal) {
        return buildResponse(authorService.getBooks(principal));
    }
}
