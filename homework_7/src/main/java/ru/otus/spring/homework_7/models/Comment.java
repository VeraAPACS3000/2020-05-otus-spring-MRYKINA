package ru.otus.spring.homework_7.models;

import javax.persistence.*;


@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "id_books", nullable = false)
    //@JoinColumn(name = "id_books", referencedColumnName = "id")
    private Long idBook;

    @Column(name = "text_comment")
    private String textComment;

    public Comment() {

    }

    public Comment(long id, long id_books, String text_comment) {
        this.id = id;
        this.idBook = id_books;
        this.textComment = text_comment;
    }

    public Comment(long id_books, String text_comment) {
        this.idBook = id_books;
        this.textComment = text_comment;
    }

    public String getTextComment() {
        return textComment;
    }

    public void setTextComment(String text_comment) {
        this.textComment = text_comment;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getIdBook() {
        return idBook;
    }

    public void setIdBook(Long idBook) {
        this.idBook = idBook;
    }

    @Override
    public String toString() {
        String displayFieldsComments = "id комментария:" + id + '\n' + "текст комментария:" + textComment + '\n';
        System.out.println(displayFieldsComments);
        return displayFieldsComments;
    }

}
