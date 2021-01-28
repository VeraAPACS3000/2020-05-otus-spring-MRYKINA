package ru.otus.spring.homework15.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "comments")
public class CommentMongo {

    @Id
    private String id;

    private String textComment;

    public CommentMongo(String id, String textComment){
        this.id = id;
        this.textComment = textComment;
    }

    public CommentMongo(){

    }

    public CommentMongo(String textComment) {
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
        return "CommentMongo{" +
                "id='" + id + '\'' +
                ", textComment='" + textComment + '\'' +
                '}';
    }
}
