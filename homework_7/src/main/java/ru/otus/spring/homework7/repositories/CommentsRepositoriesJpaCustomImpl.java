package ru.otus.spring.homework7.repositories;

import org.springframework.beans.factory.annotation.Autowired;

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

    /*public List<Comment> findByIdBook(long id) {
        Optional<Book> book = repoBook.findById(id);
        List<Comment> commentList = null;
        if (book.isPresent()) {
            commentList = book.get().getComments();
        } else {
            log.info("Oops...No comments yet");
        }
        return commentList;
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
    }*/
}
