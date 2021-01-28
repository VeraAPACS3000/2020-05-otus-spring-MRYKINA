package ru.otus.spring.homework11.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Arrays;
import java.util.List;


@Document(collection = "books")
public class Book {

    @Id
    private String id;

    @JsonProperty("name")
    @Field("name")
    private String name;

    @Field
    private int status;

    @Field
    private List<Comment> comments;

    @Field
    private Author author;

    @Field
    private Genre genre;

    public Book(String id, String name, int status, Author author, Genre genre, Comment... comments) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.comments = Arrays.asList(comments);
        this.author = author;
        this.genre = genre;
    }

    public Book() {

    }

    public Book(String name, int status, Author author, Genre genre) {
        this.name = name;
        this.status = status;
        this.author = author;
        this.genre = genre;
    }

    public Book(String name, int status, Author author, Genre genre, Comment... comments) {
        this.name = name;
        this.status = status;
        this.author = author;
        this.genre = genre;
        this.comments = Arrays.asList(comments);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        System.out.println("logs status");
        this.status = status;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(Comment... comments) {
        this.comments = Arrays.asList(comments);
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        String displayFieldsBook = "Книга:" + name + '\n' + "статус:" + status +
                '\n' + " Её комментарии:" + comments.toString() + '\n' + "Автор:" + (author != null ? author.getName() : "") +
                '\n' + "Жанр:" + (genre != null ? genre.getName() : "") + '\n';
        return displayFieldsBook;
    }
}
