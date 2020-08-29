package ru.otus.spring.homework8.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.homework8.model.Book;

import java.util.Optional;


public interface BooksRepositoriesMongo extends MongoRepository<Book, String>, BooksRepositoriesMongoCustom {
   Book findByName(String name);

   Optional<Book> findById(String id);
}
