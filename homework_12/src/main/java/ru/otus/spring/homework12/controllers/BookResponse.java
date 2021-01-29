package ru.otus.spring.homework12.controllers;

public class BookResponse {
    private final String nameBook;
    private final String nameAuthor;
    private final String nameGenre;

    public String getNameBook() {
        return nameBook;
    }

    public String getNameAuthor() {
        return nameAuthor;
    }

    public String getNameGenre() {
        return nameGenre;
    }

    BookResponse(String nameBook, String nameAuthor, String nameGenre) {
        this.nameBook = nameBook;
        this.nameAuthor = nameAuthor;
        this.nameGenre = nameGenre;
    }
}
