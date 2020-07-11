package ru.otus.spring.homework_5.domain;

public class Books {
    private long id;
    private String name;
    private int status;
    private Authors authors;
    private Genres genres;

    public Books() {

    }

    public Books(long id, String name, int status, Authors authors, Genres genres) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.authors = authors;
        this.genres = genres;
    }

    public Books(String name, Authors authors, Genres genres) {
        this.name = name;
        this.authors = authors;
        this.genres = genres;
    }

    @Override
    public String toString() {
        String displayFieldsBook = "Книга " + id + '\n' + "название:" + name + '\n' + "статус:" + status + '\n' + "автор:" +
                authors.getName() + '\n' + "жанр:" + genres.getName();
        return displayFieldsBook;
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

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setAuthors(Authors authors) {
        this.authors = authors;
    }

    public Authors getAuthors() {
        return this.authors;
    }

    public Genres getGenres() {
        return this.genres;
    }

    public void setGenres(Genres genres) {
        this.genres = genres;
    }
}
