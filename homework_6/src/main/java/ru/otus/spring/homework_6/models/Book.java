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
@Table(name = "BOOKS")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "status", nullable = false)
    private int status;

    @ManyToOne(targetEntity = Author.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_author")
    private Author author;

    @ManyToOne(targetEntity = Genre.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_genre", referencedColumnName = "id")
    private Genre genre;

    @OneToMany(targetEntity = Comment.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
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
