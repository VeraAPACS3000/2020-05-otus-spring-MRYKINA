package ru.otus.spring.homework8.repositories;

import ru.otus.spring.homework8.model.Book;

public interface BooksRepositoriesMongoCustom {
    void updateStatus(String name, int status);

    int getCommentsArrayLengthByBook(String nameBook);

    void deleteBookByName(String name);

    void insertBookWithoutDuplicateName(Book book);
}
