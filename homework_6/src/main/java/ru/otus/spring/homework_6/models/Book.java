package ru.otus.spring.homework_6.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "status", nullable = false)
    private int status;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "id_author", referencedColumnName = "id")
    private Author author;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "id_genre", referencedColumnName = "id")
    private Genre genre;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_books", referencedColumnName = "id")
    private List<Comment> comments;

    public Book(String name, Author author, Genre genre) {
        this.name = name;
        this.author = author;
        this.genre = genre;
    }

    @Override
    public String toString() {
        String displayFieldsBook = "Книга " + id + '\n' + "название:" + name + '\n' + "статус:" + status + '\n' + "автор:" +
                author.getName() + '\n' + "жанр:" + genre.getName() + '\n';
        return displayFieldsBook;
    }
}
