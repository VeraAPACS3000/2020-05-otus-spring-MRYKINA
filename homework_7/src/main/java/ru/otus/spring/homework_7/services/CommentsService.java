package ru.otus.spring.homework_7.services;

import ru.otus.spring.homework_7.models.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentsService {

    Optional<Comment> findCommentById(Long id);

    List<Comment> findCommentByIdBook(long idBook);

    List<Comment> findCommentsByNameBook(String nameBook);

    List<Comment> getAllComments();

    void deleteCommentByIdBook(long id);

    void updateCommentById(long id, String newComment);

    Comment addNewComment(String textComments, String nameBooks);
}
