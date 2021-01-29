package ru.otus.spring.homework12.dto;

import ru.otus.spring.homework12.models.Book;
import ru.otus.spring.homework12.models.Comment;

import java.util.List;

public class BookDto {

    private final long id;

    private final String name;

    private final int status;

    private final String nameAuthor;

    private final String nameGenre;

    private final List<Comment> comments;

    public BookDto(long id, String name, int status, String nameAuthor, String nameGenre, List<Comment> commentList) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.nameAuthor = nameAuthor;
        this.nameGenre = nameGenre;
        this.comments = commentList;
    }

    public String getNameAuthor() {
        return nameAuthor;
    }

    public String getNameGenre() {
        return nameGenre;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getStatus() {
        return status;
    }


    public List<Comment> getComments() {
        return comments;
    }

    public static BookDto toDto(Book book) {
        return new BookDto(book.getId(), book.getName(), book.getStatus(), book.getAuthor().getName(), book.getGenre().getName(), book.getComments());
    }
}
