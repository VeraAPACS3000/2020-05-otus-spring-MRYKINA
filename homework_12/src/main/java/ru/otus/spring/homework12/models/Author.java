package ru.otus.spring.homework12.models;

import javax.persistence.*;

@Entity
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = true)
    private String name;

    public Author() {

    }

    public Author(long id, String nameAuthor) {
        this.id = id;
        this.name = nameAuthor;
    }

    public Author(long id) {
        this.id = id;
    }

    public Author(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Имя автора:" + name;
    }
}
