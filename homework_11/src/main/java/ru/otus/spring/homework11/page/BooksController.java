package ru.otus.spring.homework11.page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.otus.spring.homework11.repositories.BooksRepositoriesReact;
import ru.otus.spring.homework11.repositories.CommentsRepositpriesReact;

@Controller
public class BooksController {

    private final BooksRepositoriesReact repositoryBook;
    private final CommentsRepositpriesReact repositoryReact;

    public BooksController(BooksRepositoriesReact repositoryBook, CommentsRepositpriesReact repositoryReact) {
        this.repositoryBook = repositoryBook;
        this.repositoryReact = repositoryReact;
    }

    @GetMapping("/")
    public String listAllBooks() {
        return "listBooks";
    }

    @GetMapping("/bookInfo/{id}")
    public String getBookById(@PathVariable("id") String id, Model model) {
        return "details";
    }

    @GetMapping("/addNewBook")
    public String addBook() {
        return "newBook";
    }

}
