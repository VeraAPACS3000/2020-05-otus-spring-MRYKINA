package ru.otus.spring.homework8.services;

import org.springframework.stereotype.Service;
import ru.otus.spring.homework8.model.Comment;
import ru.otus.spring.homework8.repositories.BooksRepositoriesMongo;
import ru.otus.spring.homework8.repositories.CommentsRepositpriesMongo;

import java.util.List;

@Service
public class CommentsServicesImpl implements CommentsServices {

    BooksRepositoriesMongo repoBook;
    CommentsRepositpriesMongo repoComments;

    CommentsServicesImpl(BooksRepositoriesMongo repoBook, CommentsRepositpriesMongo repoComments) {
        this.repoBook = repoBook;
        this.repoComments = repoComments;
    }

    @Override
    public List<Comment> findAllComments() {
        return repoComments.findAll();
    }

    @Override
    public int countAllComments() {
        return repoComments.sizeComments();
    }

    @Override
    public void add(String nameComment) {
        Comment comment = new Comment(nameComment);
        repoComments.insert(comment);
    }

}
