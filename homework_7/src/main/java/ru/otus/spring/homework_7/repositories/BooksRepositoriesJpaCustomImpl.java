package ru.otus.spring.homework_7.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import ru.otus.spring.homework_7.models.Book;
import ru.otus.spring.homework_7.models.Comment;

import java.util.List;
import java.util.Optional;

public class BooksRepositoriesJpaCustomImpl implements BooksRepositoriesJpaCustom {

    @Autowired
    BooksRepositoriesJpa repoBook;

    @Autowired
    CommentsRepositoriesJpa repoComment;

    BooksRepositoriesJpaCustomImpl(BooksRepositoriesJpa repoBook, CommentsRepositoriesJpa repoComment) {
        this.repoBook = repoBook;
        this.repoComment = repoComment;
    }

    public void deleteBookById(long id) {
        Optional<Book> book = repoBook.findById(id);
        if (book.isPresent()) {
            List<Comment> listComments = book.get().getComments();
            for (Comment comment : listComments) {
                repoComment.deleteById(comment.getId());
            }
            repoBook.delete(book.get());
        } else {
            System.out.println("Not found book to delete");
        }
    }

    public void updateBookStatus(long id, int status) {
        Optional<Book> book = repoBook.findById(id);
        if (book.isPresent()) {
            book.get().setStatus(status);
            repoBook.save(book.get());
        } else {
            System.out.println("Not found book to update status");
        }

    }
}
