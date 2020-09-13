package ru.otus.spring.homework10.page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.otus.spring.homework10.services.BooksService;
import ru.otus.spring.homework10.services.CommentsService;

@Controller
public class BooksController {

    @Autowired
    private BooksService booksService;

    @Autowired
    private CommentsService commentsService;

    public BooksController(BooksService booksService, CommentsService commentsService) {
        this.booksService = booksService;
        this.commentsService = commentsService;
    }

    @GetMapping("/")
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

}
