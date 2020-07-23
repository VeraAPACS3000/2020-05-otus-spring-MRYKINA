package ru.otus.spring.homework_7.models;

import javax.persistence.*;


@Entity
@Table(name = "genres")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = true)
    private String name;

    public Genre() {
    }

    public Genre(long id, String nameGenre) {
        this.id = id;
        this.name = nameGenre;
    }

    public Genre(long id) {
        this.id = id;
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

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        String result = "Имя автора:" + name;
        System.out.println(result);
        return result;
    }
}
