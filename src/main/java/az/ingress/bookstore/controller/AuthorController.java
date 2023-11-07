package az.ingress.bookstore.controller;


import az.ingress.bookstore.dto.response.BookResponse;
import az.ingress.bookstore.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

import static az.ingress.bookstore.util.ResponseBuilder.buildResponse;

@RestController
@RequestMapping("api/v1/authors")
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;

    @GetMapping("/books")
    @PreAuthorize("hasAuthority('AUTHOR')")
    public ResponseEntity<List<BookResponse>> getBooks(Principal principal) {
        return buildResponse(authorService.getBooks(principal));
    }
}
