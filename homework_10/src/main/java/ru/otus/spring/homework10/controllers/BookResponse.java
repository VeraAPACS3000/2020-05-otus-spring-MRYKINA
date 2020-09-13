package ru.otus.spring.homework10.controllers;

public class BookResponse {
    private String nameBook;
    private String nameAuthor;
    private String nameGenre;

    public String getNameBook() {
        return nameBook;
    }

    public String getNameAuthor() {
        return nameAuthor;
    }

    public String getNameGenre() {
        return nameGenre;
    }

    BookResponse() {

    }

    BookResponse(String nameBook, String nameAuthor, String nameGenre) {
        this.nameBook = nameBook;
        this.nameAuthor = nameAuthor;
        this.nameGenre = nameGenre;
    }
}
