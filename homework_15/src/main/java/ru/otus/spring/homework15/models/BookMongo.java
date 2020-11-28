package ru.otus.spring.homework15.models;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.Arrays;
import java.util.List;

@Document(collection = "books")
public class BookMongo {

    @Id
    private String id;

    private String name;

    private int status;

    @DBRef
    private List<Comment> comments;

    @DBRef
    private Author author;

    @DBRef
    private Genre genre;

    public BookMongo(String id, String name, int status, Author author, Genre genre, List<Comment> commentsList) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.comments = commentsList;
        this.author = author;
        this.genre = genre;
    }

    public BookMongo() {

    }

    public BookMongo(String name, int status, Author author, Genre genre) {
        this.name = name;
        this.status = status;
        this.author = author;
        this.genre = genre;
    }

    public BookMongo(String name, int status, Author author, Genre genre, Comment... comments) {
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

}

