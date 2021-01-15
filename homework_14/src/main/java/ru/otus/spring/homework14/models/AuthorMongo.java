package ru.otus.spring.homework14.models;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document(collection = "authors")
public class AuthorMongo {

    @Id
    private String id;

    private String name;

    public AuthorMongo() {

    }

    public AuthorMongo(String id, String nameAuthor) {
        this.id = id;
        this.name = nameAuthor;
    }

    public AuthorMongo(String name) {
        this.name = name;
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


    @Override
    public String toString() {
        return "AuthorMongo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
