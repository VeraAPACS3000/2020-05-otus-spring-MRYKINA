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
    private long idBooks;

    @Column(name = "text_comment", nullable = true)
    private String textComment;

    @Override
    public String toString() {
        return this.textComment;
    }

    public Comment(){

    }

    public Comment(long idBooks, String textComment) {
        this.idBooks = idBooks;
        this.textComment = textComment;
    }
}
