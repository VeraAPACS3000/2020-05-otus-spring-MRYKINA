package ru.otus.spring.homework7.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.otus.spring.homework7.models.Author;

import java.util.Optional;

@Repository
public interface AuthorRepositoriesJpa extends CrudRepository<Author, Long> {
    Optional<Author> findByName(String nameAuthor);
}
