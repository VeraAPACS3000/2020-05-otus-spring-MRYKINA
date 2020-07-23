package ru.otus.spring.homework_7.services;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.homework_7.models.Book;
import ru.otus.spring.homework_7.models.Comment;
import ru.otus.spring.homework_7.repositories.BooksRepositoriesJpa;
import ru.otus.spring.homework_7.repositories.CommentsRepositoriesJpa;

import java.util.List;
import java.util.Optional;

@Service
public class CommentsServiceImpl implements CommentsService {

    CommentsRepositoriesJpa repoComment;

    BooksRepositoriesJpa repoBook;

    CommentsServiceImpl(CommentsRepositoriesJpa repoComment, BooksRepositoriesJpa repoBook) {
        this.repoComment = repoComment;
        this.repoBook = repoBook;
    }

    @Override
    public Optional<Comment> findCommentById(Long id) {
        return repoComment.findById(id);
    }

    @Override
    public List<Comment> findCommentByIdBook(long idBook) {
        System.out.println("два");
        return repoComment.findByIdBook(idBook);
    }

    @Override
    public List<Comment> findCommentsByNameBook(String nameBook) {
        return repoComment.findByNameBook(nameBook);
    }

    @Override
    public List<Comment> getAllComments() {
        return repoComment.findAll();
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
        repoComment.updateComment(newComment, id);
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
            System.out.println("Oops..no the book for your comment");
        }
        return comment;
    }
}
