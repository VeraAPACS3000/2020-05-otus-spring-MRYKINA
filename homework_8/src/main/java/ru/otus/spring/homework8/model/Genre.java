package ru.otus.spring.homework8.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "genres")
public class Genre {

    @Id
    private String id;

    private String name;

    public Genre() {
    }

    public Genre(String id, String nameGenre) {
        this.id = id;
        this.name = nameGenre;
    }

    public Genre(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Жанр:" + name;
    }
}
