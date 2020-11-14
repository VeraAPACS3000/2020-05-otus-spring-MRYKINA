package ru.otus.spring.homework11.repositoriesnonreact;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.homework11.models.Author;

public interface AuthorsRepositoriesNonReact extends MongoRepository<Author, String> {

}
