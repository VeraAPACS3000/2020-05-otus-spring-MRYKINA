package ru.otus.spring.homework8.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.homework8.model.Comment;
import ru.otus.spring.homework8.services.CommentsServices;

import java.util.List;

@ShellComponent
public class CommentsShellCommands {

    CommentsServices commentsServices;

    CommentsShellCommands(CommentsServices commentsServices) {
        this.commentsServices = commentsServices;
    }

    @ShellMethod(value = "Select all comments", key = {"sc", "selectComments"})
    public String selectAllComments() {
        List<Comment> commentList = commentsServices.findAllComments();
        System.out.println(commentList);
        return "Показали все комментарии";
    }

    @ShellMethod(value = "Get count all comments", key = {"gac", "getCountComments1"})
    public String getCountComments1() {
        int count = commentsServices.countAllComments();
        System.out.println(count);
        return "Показали число комментариев в базе";
    }

    @ShellMethod(value = "Add comment", key = {"ac", "addComment"})
    public String addComment() {
        commentsServices.add("shell text comment");
        return "Добавили комментарий в базу";
    }
}
