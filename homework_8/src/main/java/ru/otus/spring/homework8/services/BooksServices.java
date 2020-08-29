package ru.otus.spring.homework8.services;

import ru.otus.spring.homework8.model.Book;

import java.util.List;
import java.util.Optional;

public interface BooksServices {

    List<Book> findAll();

    Book findByName(String name);

    Optional<Book> findById(String id);

    void deleteByNameBook(String nameBook);

    void deleteByBook(Book book);

    void add(String nameBook, String nameAuthor, String nameGenre);

    void insert(Book book);

    void update(String name, int status);

    int getCountCommentsAboutBook(String name);
}
