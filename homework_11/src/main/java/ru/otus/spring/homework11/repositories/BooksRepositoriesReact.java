package ru.otus.spring.homework11.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;
import ru.otus.spring.homework11.models.Book;

public interface BooksRepositoriesReact extends ReactiveMongoRepository<Book, String> {
    Mono<Book> findByName(String name);
}
