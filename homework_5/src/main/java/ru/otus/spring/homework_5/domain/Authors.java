package ru.otus.spring.homework_5.domain;

public class Authors {

    private long id;
    private String name;

    public Authors(long id) {
        this.id = id;
    }

    public Authors(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
