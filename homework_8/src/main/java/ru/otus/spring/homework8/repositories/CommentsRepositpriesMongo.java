package ru.otus.spring.homework8.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.homework8.model.Comment;

import java.util.List;

public interface CommentsRepositpriesMongo extends MongoRepository<Comment, String>, CommentsRepositoriesMongoCustom {
    List<Comment> findAll();
}
