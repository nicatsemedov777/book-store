package az.ingress.bookstore.service;

import az.ingress.bookstore.dto.request.BookCreateRequest;
import az.ingress.bookstore.dto.response.BookResponse;
import az.ingress.bookstore.dto.response.StudentResponse;
import az.ingress.bookstore.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

import java.security.Principal;
import java.util.List;

public interface BookService {
    BookResponse createBook(BookCreateRequest bookCreateRequest, Principal principal);

    List<StudentResponse> getReadersByBookId(String bookId);

    List<BookResponse> getAllBooks();

    void deleteBook(String bookId, Principal principal);
}
