package ru.otus.spring.homework8.services;

import ru.otus.spring.homework8.model.Comment;

import java.util.List;

public interface CommentsServices {

    List<Comment> findAllComments();

    int countAllComments();//Query

    void add(String textComment);
}
