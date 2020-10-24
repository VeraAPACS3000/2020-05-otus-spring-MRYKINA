package ru.otus.spring.homework11.repositoriesnonreact;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.homework11.models.Book;


public interface BooksRepositoriesNonReact extends MongoRepository<Book, String> {

}
