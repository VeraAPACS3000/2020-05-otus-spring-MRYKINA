package ru.otus.spring.homework_7.repositories;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.otus.spring.homework_7.models.Book;

import java.util.Optional;

@Repository
public interface BooksRepositoriesJpa extends BooksRepositoriesJpaCustom, CrudRepository<Book, Long> {
    void updateBookStatus(long id, int status);

    @EntityGraph(value = "book-comments-entity-graph")
    Optional<Book> findByName(String name);
}
