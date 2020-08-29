package ru.otus.spring.homework8.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.otus.spring.homework8.model.Comment;


public class CommentsRepositoriesMongoCustomImpl implements CommentsRepositoriesMongoCustom {

    @Autowired
    private final MongoTemplate mongoTemplate;

    public CommentsRepositoriesMongoCustomImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
    @Override
    public int sizeComments() {
        return mongoTemplate.query(Comment.class).all().size();
    }
}
