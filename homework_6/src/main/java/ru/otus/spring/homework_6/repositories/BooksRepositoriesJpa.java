package ru.otus.spring.homework_6.repositories;

import ru.otus.spring.homework_6.models.Book;

import java.util.List;
import java.util.Optional;

public interface BooksRepositoriesJpa {

    List<Book> getAll();

    Optional<Book> findById(long id);

    Book findByName(String name);

    Book insert(Book book);

    void updateStatus(long id, int status);

    void deleteById(long id);

}
