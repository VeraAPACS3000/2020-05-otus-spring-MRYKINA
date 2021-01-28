package ru.otus.spring.homework11.repositoriesnonreact;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.homework11.models.Genre;

public interface GenresRepositoriesNonReact extends MongoRepository<Genre, String> {

}
