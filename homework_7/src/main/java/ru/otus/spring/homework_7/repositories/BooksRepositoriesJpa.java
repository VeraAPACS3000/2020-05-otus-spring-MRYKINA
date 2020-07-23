package ru.otus.spring.homework_7.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.otus.spring.homework_7.models.Book;

import java.util.List;
import java.util.Optional;

@Repository
public interface BooksRepositoriesJpa extends BooksRepositoriesJpaCustom, CrudRepository<Book, Long> {

    @Override
    List<Book> findAll();

    @Override
    Optional<Book> findById(Long id);

    @Override
    Book save(Book book);

    @Override
    void deleteBookById(long id);

    @Override
    void updateBookStatus(long id, int status);

    @Query("select b from Book b where b.name = :name")
    Optional<Book> findByName(@Param("name") String name);
}
