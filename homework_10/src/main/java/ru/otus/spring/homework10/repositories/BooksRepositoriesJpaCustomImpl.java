package ru.otus.spring.homework10.repositories;

import ru.otus.spring.homework10.models.Book;

import java.util.Optional;
import java.util.logging.Logger;

public class BooksRepositoriesJpaCustomImpl implements BooksRepositoriesJpaCustom {

    private static Logger log = Logger.getLogger(BooksRepositoriesJpaCustomImpl.class.getName());

    private final BooksRepositoriesJpa repoBook;
    private final CommentsRepositoriesJpa repoComment;

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
