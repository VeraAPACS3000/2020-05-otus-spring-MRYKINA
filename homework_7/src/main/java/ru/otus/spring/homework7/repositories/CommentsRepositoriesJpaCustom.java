package ru.otus.spring.homework7.repositories;

import ru.otus.spring.homework7.models.Comment;

import java.util.List;

public interface CommentsRepositoriesJpaCustom {
    List<Comment> findByNameBook(String nameBook);

    List<Comment> findByIdBook(long id);

    void updateComment(String textComment, long id);

}
