package ru.otus.spring.homework_7.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.otus.spring.homework_7.models.Genre;

import java.util.List;
import java.util.Optional;

@Repository
public interface GenreRepositoriesJpa extends CrudRepository<Genre, Long> {

    @Override
    List<Genre> findAll();

    @Override
    Optional<Genre> findById(Long id);

    @Override
    void deleteById(Long id);

    @Override
    Genre save(Genre genre);

    @Query("select a from Genre a where a.name = :name")
    Optional<Genre> findByName(@Param("name") String name);
}
