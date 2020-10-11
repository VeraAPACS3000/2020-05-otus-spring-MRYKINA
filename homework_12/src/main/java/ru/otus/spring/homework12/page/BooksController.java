package ru.otus.spring.homework12.page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.otus.spring.homework12.services.BooksService;
import ru.otus.spring.homework12.services.CommentsService;

@Controller
public class BooksController {

    private final BooksService booksService;
    private final CommentsService commentsService;

    public BooksController(BooksService booksService, CommentsService commentsService) {
        this.booksService = booksService;
        this.commentsService = commentsService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/listBooks")
    public String listAllBooks() {
        return "listBooks";
    }

    @GetMapping("/bookInfo/{id}")
    public String getBookById(@PathVariable("id") long id, Model model) {
        model.addAttribute("id", id);
        return "details";
    }

    @GetMapping("/addNewBook")
    public String addBook() {
        return "newBook";
    }

    @GetMapping("/error")
    public String errorPage() {
        return "error";
    }
}