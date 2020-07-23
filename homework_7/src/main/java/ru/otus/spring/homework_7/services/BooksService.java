package ru.otus.spring.homework_7.services;

import ru.otus.spring.homework_7.models.Book;

import java.util.List;
import java.util.Optional;

public interface BooksService {
    List<Book> getAllBooks();

    Optional<Book> findBookById(long id);

    Optional<Book> findBookByName(String nameBook);

    void deleteBookById(long id);

    void updateStatusBook(long id, int status);

    void addNewBook(String nameBook, String nameAuthor, String nameGenre);
}
