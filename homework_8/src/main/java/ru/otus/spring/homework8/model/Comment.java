package ru.otus.spring.homework8.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "comments")
public class Comment {

    @Id
    private String id;

    private String textComment;

    public Comment(String id, String textComment){
        this.id = id;
        this.textComment = textComment;
    }

    public Comment(){

    }

    public Comment(String textComment) {
        this.textComment = textComment;
    }

    public String getTextComment() {
        return textComment;
    }

    public void setTextComment(String textComment) {
        this.textComment = textComment;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Комментарий:" + textComment + '\n';
    }

}
