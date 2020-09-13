package ru.otus.spring.homework9.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.spring.homework9.dto.BookDto;
import ru.otus.spring.homework9.models.Book;
import ru.otus.spring.homework9.services.BooksService;
import ru.otus.spring.homework9.services.CommentsService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public String listBooks(Model model) {
        List<Book> listBook = booksService.getAllBooks();
        List<BookDto> listBookDto = listBook.stream().map(BookDto::toDto).collect(Collectors.toList());
        model.addAttribute("books", listBookDto);
        return "listBooks";
    }

    @GetMapping("/bookInfo")
    public String getBookById(@RequestParam("id") long id, Model model) {
        Optional<Book> book = booksService.findBookById(id);
        BookDto bookDto = BookDto.toDto(book.get());
        model.addAttribute("book", bookDto);
        return "details";
    }

    @PostMapping("/bookInfo")
    public String detailsBook(@RequestParam(value = "action", required = true) String action,
                              @RequestParam(value = "id", required = true) long id,
                              @RequestParam(value = "status", required = true) int status,
                              @RequestParam(value = "textComment", required = true) String textComment,
                              Model model) {

        if (action.equals("update")) {
            booksService.updateStatusBook(id, status);
        }

        if (action.equals("delete")) {
            booksService.deleteBookById(id);
            return "redirect:/";
        }

        if (action.equals("comment")) {
            commentsService.addNewCommentByIdBook(textComment, id);
        }
        return "redirect:/bookInfo?id=" + id;
    }

    @GetMapping("/newBook")
    public String addNewBook() {
        return "newBook";
    }

    @PostMapping("/newBook")
    public String addNewBook(
            String nameBook,
            String nameAuthor,
            String nameGenre,
            Model model) {
        booksService.addNewBook(nameBook, nameAuthor, nameGenre);
        return "redirect:/";
    }
}
