package ru.otus.spring.homework_7.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import ru.otus.spring.homework_7.models.Book;

import java.util.Optional;
import java.util.logging.Logger;

public class BooksRepositoriesJpaCustomImpl implements BooksRepositoriesJpaCustom {

    private static Logger log = Logger.getLogger(BooksRepositoriesJpaCustomImpl.class.getName());

    @Autowired
    BooksRepositoriesJpa repoBook;

    @Autowired
    CommentsRepositoriesJpa repoComment;

    BooksRepositoriesJpaCustomImpl(BooksRepositoriesJpa repoBook, CommentsRepositoriesJpa repoComment) {
        this.repoBook = repoBook;
        this.repoComment = repoComment;
    }

    public void updateBookStatus(long id, int status) {
        Optional<Book> book = repoBook.findById(id);
        if (book.isPresent()) {
            book.get().setStatus(status);
            repoBook.save(book.get());
        } else {
            log.info("Not found book to update status");
        }
    }
}
