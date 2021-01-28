package ru.otus.spring.homework11.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import ru.otus.spring.homework11.models.Genre;

public interface GenresRepositoriesReact extends ReactiveMongoRepository<Genre, String> {

}
