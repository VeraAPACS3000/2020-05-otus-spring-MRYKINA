package ru.otus.spring.homework_7.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.otus.spring.homework_7.models.Author;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepositoriesJpa extends CrudRepository<Author, Long> {

    @Override
    List<Author> findAll();

    @Override
    Optional<Author> findById(Long id);

    @Override
    void deleteById(Long id);

    @Override
    Author save(Author author);

    @Query("select a from Author a where a.name = :name")
    Optional<Author> findByName(@Param("name") String name);
}
