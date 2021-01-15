package ru.otus.spring.homework14.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "genres")
public class GenreMongo {

    @Id
    private String id;

    private String name;

    public GenreMongo() {
    }

    public GenreMongo(String id, String nameGenre) {
        this.id = id;
        this.name = nameGenre;
    }

    public GenreMongo(String name) {
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
        return "GenreMongo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}

