package ru.otus.spring.homework_6.services;

import ru.otus.spring.homework_6.models.Comment;

import java.util.List;

public interface CommentsService {
    List<Comment> findCommentByIdBook(long idBook);

    List<Comment> findCommentsByNameBook(String nameBook);

    List<Comment> getAllComments();

    void deleteCommentByIdBook(long id);

    void updateCommentById(long id, String newComment);

    Comment addNewComment(String textComments, String nameBooks);
}
