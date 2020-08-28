package ru.otus.spring.homework9.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.otus.spring.homework9.models.Book;

import java.util.Optional;

@Repository
public interface BooksRepositoriesJpa extends BooksRepositoriesJpaCustom, CrudRepository<Book, Long> {
    void updateBookStatus(long id, int status);

    Optional<Book> findByName(String name);
}
