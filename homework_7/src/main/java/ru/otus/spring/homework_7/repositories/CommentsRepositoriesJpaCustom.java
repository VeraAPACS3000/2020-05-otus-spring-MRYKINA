package ru.otus.spring.homework_7.repositories;

import ru.otus.spring.homework_7.models.Comment;

import java.util.List;

public interface CommentsRepositoriesJpaCustom {
    List<Comment> findByNameBook(String nameBook);

    void updateComment(String textComment, long id);

    List<Comment> findByIdBook(long id);
}
