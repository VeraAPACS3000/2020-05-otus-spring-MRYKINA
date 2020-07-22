package ru.otus.spring.homework_6.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.homework_6.models.Book;
import ru.otus.spring.homework_6.models.Comment;
import ru.otus.spring.homework_6.repositories.BooksRepositoriesJpa;
import ru.otus.spring.homework_6.repositories.CommentsRepositoriesJpa;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Service
public class CommentsServiceImpl implements CommentsService {

    BooksRepositoriesJpa repositoriesBooks;
    CommentsRepositoriesJpa repositoriesComments;
    EntityManager em;

    public CommentsServiceImpl(BooksRepositoriesJpa repositoriesBooks, CommentsRepositoriesJpa repositoriesComments, EntityManager em) {
        this.repositoriesComments = repositoriesComments;
        this.repositoriesBooks = repositoriesBooks;
        this.em = em;
    }

    @Override
    @Transactional
    public List<Comment> findCommentByIdBook(long idBook) {
        int count = 0;
        List<Comment> commentList = null;
        Optional<Book> book = Optional.ofNullable(em.find(Book.class, idBook));
        if (book.isPresent()) {
            commentList = book.get().getComments();
            for (Comment comment : commentList) {
                System.out.println(++count + ")" + comment.toString());
            }
        } else {
            System.out.println("Oops...No comments yet");
        }
        return commentList;
    }

    @Override
    @Transactional
    public List<Comment> findCommentsByNameBook(String nameBook) {
        Optional<Book> book = Optional.ofNullable(repositoriesBooks.findByName(nameBook));
        List<Comment> commentList = null;
        int count = 0;
        if (book.isPresent()) {
            commentList = book.get().getComments();
            for (Comment comment : commentList) {
                System.out.println(++count + ")" + comment.toString());
            }
        } else {
            System.out.println("Oops...No comments yet");
        }
        return commentList;
    }

    @Override
    @Transactional
    public List<Comment> getAllComments() {
        List<Comment> commentList = repositoriesComments.getAll();
        for (Comment comment : commentList) {
            System.out.println(comment.toString());
        }
        return commentList;
    }

    @Override
    @Transactional
    public void deleteCommentByIdBook(long id) {
        repositoriesComments.deleteById(id);
    }

    @Override
    @Transactional
    public void updateCommentById(long id, String newComment) {
        repositoriesComments.updateComment(id, newComment);
    }

    @Override
    @Transactional
    public Comment addNewComment(String textComment, String nameBook) {
        Comment comment = null;
        Book book = repositoriesBooks.findByName(nameBook);
        if (book != null) {
            comment = new Comment(book.getId(), textComment);
            repositoriesComments.insert(comment);
        } else {
            System.out.println("Oops..no the book for your comment");
        }
        return comment;
    }

}
