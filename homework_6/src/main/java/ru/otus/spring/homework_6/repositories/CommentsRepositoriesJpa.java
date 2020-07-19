package ru.otus.spring.homework_6.repositories;

import ru.otus.spring.homework_6.models.Comment;

import java.util.List;

public interface CommentsRepositoriesJpa {

    List<Comment> findByIdBook(long id);

    List<Comment> findByNameBook(String name);

    List<Comment> getAll();

    void deleteById(long id);

    void updateComment(long id, String newComment);

    Comment insert(Comment comment);
}
