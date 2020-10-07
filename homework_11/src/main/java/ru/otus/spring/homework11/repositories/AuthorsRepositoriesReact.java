package ru.otus.spring.homework11.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import ru.otus.spring.homework11.models.Author;

public interface AuthorsRepositoriesReact extends ReactiveMongoRepository<Author, String> {

}
