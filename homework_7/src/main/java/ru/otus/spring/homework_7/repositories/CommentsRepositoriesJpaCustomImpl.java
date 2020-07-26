package ru.otus.spring.homework_7.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import ru.otus.spring.homework_7.models.Book;
import ru.otus.spring.homework_7.models.Comment;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;


public class CommentsRepositoriesJpaCustomImpl implements CommentsRepositoriesJpaCustom {

    private static Logger log = Logger.getLogger(CommentsRepositoriesJpaCustomImpl.class.getName());

    @Autowired
    BooksRepositoriesJpa repoBook;

    @Autowired
    CommentsRepositoriesJpa repoComment;


    CommentsRepositoriesJpaCustomImpl(BooksRepositoriesJpa repoBook, CommentsRepositoriesJpa repoComment) {
        this.repoBook = repoBook;
        this.repoComment = repoComment;
    }

    public List<Comment> findByNameBook(String nameBook) {
        Optional<Book> book = repoBook.findByName(nameBook);
        List<Comment> commentList = null;
        if (book.isPresent()) {
            commentList = book.get().getComments();
        } else {
            log.info("Oops...No comments yet");
        }
        return commentList;
    }

    public void updateComment(String textComment, long id) {
        Optional<Comment> comment = repoComment.findById(id);
        if (comment.isPresent()) {
            comment.get().setTextComment(textComment);
            repoComment.save(comment.get());
        } else {
            log.info("Not found comment to update");
        }
    }
}
