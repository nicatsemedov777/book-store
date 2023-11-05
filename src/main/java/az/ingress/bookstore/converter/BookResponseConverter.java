package az.ingress.bookstore.converter;

import az.ingress.bookstore.dto.response.BookResponse;
import az.ingress.bookstore.entity.Book;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class BookResponseConverter implements Function<Book, BookResponse> {
    @Override
    public BookResponse apply(Book book) {
        return BookResponse.builder()
                .bookName(book.getName())
                .authorId(book.getAuthor().getId())
                .build();
    }
}
