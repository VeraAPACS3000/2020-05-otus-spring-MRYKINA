package ru.otus.spring.homework15.services;

import org.springframework.stereotype.Service;
import ru.otus.spring.homework15.models.*;

@Service
public class ConvertToMongo {

    public AuthorMongo doConvert(Author author) {
        AuthorMongo authorMongo = new AuthorMongo(
                String.valueOf(author.getId()),
                author.getName()
        );
        return authorMongo;
    }

    public GenreMongo doConvert(Genre genre) {
        GenreMongo genreMongo = new GenreMongo(
                String.valueOf(genre.getId()),
                genre.getName()
        );
        return genreMongo;
    }

    public CommentMongo doConvert(Comment comment) {
        CommentMongo commentMongo = new CommentMongo(
                String.valueOf(comment.getId()),
                comment.getTextComment()
        );
        return commentMongo;
    }

    public BookMongo doConvert(Book book) {
        BookMongo bookMongo = new BookMongo(
                String.valueOf(book.getId()),
                book.getName(),
                book.getStatus(),
                book.getAuthor(),
                book.getGenre(),
                book.getComments()
        );
        return bookMongo;
    }
}
