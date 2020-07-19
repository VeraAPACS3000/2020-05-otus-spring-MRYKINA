package ru.otus.spring.homework_6.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "id_books", nullable = false)
    private long id_books;

    @Column(name = "text_comment", nullable = true)
    private String text_comment;

    @Override
    public String toString() {
        return this.text_comment;
    }

    public Comment(long id_books, String text_comment) {
        this.id_books = id_books;
        this.text_comment = text_comment;
    }

}
