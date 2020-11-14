package ru.otus.spring.homework11.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import ru.otus.spring.homework11.models.Comment;

@Repository
public interface CommentsRepositpriesReact extends ReactiveMongoRepository<Comment, String>{

}
