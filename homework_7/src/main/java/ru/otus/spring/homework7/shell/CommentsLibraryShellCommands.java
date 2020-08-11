package ru.otus.spring.homework7.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.homework7.models.Comment;
import ru.otus.spring.homework7.services.CommentsService;

import java.util.List;

@ShellComponent
public class CommentsLibraryShellCommands {
    CommentsService commentsService;

    CommentsLibraryShellCommands(CommentsService commentsService) {
        this.commentsService = commentsService;
    }

    @ShellMethod(value = "Select all comments", key = {"sc", "selectComments"})
    public String selectAllComments() {
        List<Comment> listComments = commentsService.getAllComments();
        System.out.println(listComments);
        return "Показали все-все комментарии";
    }

    @ShellMethod(value = "Find comments by Id book. Example: fcid 1", key = {"fcid", "findCommentByIdBook"})
    public String findCommentByIdBook(long id) {
        List<Comment> listComments = commentsService.findCommentByIdBook(id);
        System.out.println(listComments);
        return "Нашли все комментарии к книге по id";
    }

    @ShellMethod(value = "Find comments by name book. Example: fcname 'Misery'", key = {"fcn", "findCommentByNameBook"})
    public String findCommentByNameBook(String nameBook) {
        List<Comment> listComments = commentsService.findCommentsByNameBook(nameBook);
        if (listComments == null)
            return "Не нашли ни одного комментария к книге по названию книги";
        System.out.println(listComments);
        return "Нашли все комментарии к книге по названию книги";
    }

    @ShellMethod(value = "Delete comment", key = {"dc", "deleteComment"})
    public String deleteCommentByIdBook(long id) {
        commentsService.deleteCommentByIdBook(id);
        return "Удалили комментарий к книге по id";
    }

    @ShellMethod(value = "Update comment.Example uc 1 'super-puper book'", key = {"uc", "updateComment"})
    public String updateComment(long id, String newComment) {
        commentsService.updateCommentById(id, newComment);
        return "Изменили текст комментария";
    }

    @ShellMethod(value = "Add new comment. Example i 'new book' 'Lermontov' 'prose'", key = {"ic", "insertComment"})
    public String addNewComment(String textComments, String nameBook) {
        commentsService.addNewComment(textComments, nameBook);
        return "Добавлен новый комментарий";
    }
}
