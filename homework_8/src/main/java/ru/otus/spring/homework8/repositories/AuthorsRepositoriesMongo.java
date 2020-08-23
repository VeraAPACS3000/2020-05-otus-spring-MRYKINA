package ru.otus.spring.homework8.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.homework8.model.Author;

public interface AuthorsRepositoriesMongo extends MongoRepository<Author, String> {

}
