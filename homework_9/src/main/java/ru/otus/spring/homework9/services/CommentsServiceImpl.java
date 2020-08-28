package ru.otus.spring.homework9.services;

import org.hibernate.Hibernate;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.homework9.models.Book;
import ru.otus.spring.homework9.models.Comment;
import ru.otus.spring.homework9.repositories.BooksRepositoriesJpa;
import ru.otus.spring.homework9.repositories.CommentsRepositoriesJpa;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class CommentsServiceImpl implements CommentsService {

    private static Logger log = Logger.getLogger(CommentsServiceImpl.class.getName());

    CommentsRepositoriesJpa repoComment;

    BooksRepositoriesJpa repoBook;

    CommentsServiceImpl(CommentsRepositoriesJpa repoComment, BooksRepositoriesJpa repoBook) {
        this.repoComment = repoComment;
        this.repoBook = repoBook;
    }

    @Override
    public Optional<Comment> findCommentById(Long id) {
        Optional<Comment> comment = repoComment.findById(id);
        if (comment == null) {
            log.info("Not found comment by id");
        }
        return comment;
    }

    @Override
    @Transactional
    public List<Comment> findCommentByIdBook(long idBook) {
        Optional<Book> book = repoBook.findById(idBook);
        List<Comment> commentList = null;
        if (book.isPresent()) {
            Hibernate.initialize(book.get().getComments());
            commentList = book.get().getComments();
        } else {
            log.info("Oops...No comments yet");
        }
        return commentList;
    }

    @Override
    @Transactional
    public List<Comment> findCommentsByNameBook(String nameBook) {
        Optional<Book> book = repoBook.findByName(nameBook);
        List<Comment> commentList = null;
        if (book.isPresent()) {
            commentList = book.get().getComments();
            commentList.size();//здесь обращаюсь
        } else {
            System.out.println("Oops...No comments yet");
        }

        return commentList;
    }

    @Override
    public List<Comment> getAllComments() {
        List<Comment> commentList = repoComment.findAll();
        if (commentList == null) {
            log.info("Not found list comments");
        }
        return commentList;
    }

    @Override
    @Transactional
    public void deleteCommentByIdBook(long id) {
        repoComment.deleteById(id);
    }

    @Override
    @Modifying(clearAutomatically = true)
    @Transactional
    public void updateCommentById(long id, String newComment) {
        Optional<Comment> comment = repoComment.findById(id);
        if (comment.isPresent()) {
            comment.get().setTextComment(newComment);
            repoComment.save(comment.get());
        } else {
            log.info("Not found comment to update");
        }
    }

    @Override
    @Transactional
    public Comment addNewComment(String textComment, String nameBook) {
        Comment comment = null;
        Optional<Book> book = repoBook.findByName(nameBook);
        if (book.isPresent()) {
            comment = new Comment(book.get().getId(), textComment);
            repoComment.save(comment);
        } else {
            log.info("Oops..no the book for your comment");
        }
        return comment;
    }

    @Override
    @Transactional
    public Comment addNewCommentByIdBook(String textComment, long idBook) {
        Comment comment = null;
        Optional<Book> book = repoBook.findById(idBook);
        if (book.isPresent()) {
            comment = new Comment(book.get().getId(), textComment);
            repoComment.save(comment);
        } else {
            log.info("Oops..no the book for your comment");
        }
        return comment;
    }
}
