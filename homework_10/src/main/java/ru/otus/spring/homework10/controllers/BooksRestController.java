package ru.otus.spring.homework10.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.homework10.dto.BookDto;
import ru.otus.spring.homework10.models.Book;
import ru.otus.spring.homework10.services.BooksService;
import ru.otus.spring.homework10.services.CommentsService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class BooksRestController {

    @Autowired
    private BooksService booksService;

    @Autowired
    private CommentsService commentsService;

    public BooksRestController(BooksService booksService, CommentsService commentsService) {
        this.booksService = booksService;
        this.commentsService = commentsService;
    }

    @GetMapping("/api/books")
    public List<BookDto> listBooks() {
        List<Book> listBook = booksService.getAllBooks();
        List<BookDto> listBookDto = listBook.stream().map(BookDto::toDto).collect(Collectors.toList());
        return listBookDto;
    }

    @GetMapping("/api/bookInfo/{id}")
    public BookDto getBookById(@PathVariable long id) {
        Optional<Book> book = booksService.findBookById(id);
        BookDto bookDto = BookDto.toDto(book.get());
        return bookDto;
    }

    @PostMapping(value = "/api/bookInfo/{id}/update")
    public String detailsBook(@RequestBody int status,
                              @PathVariable("id") long id) {
        booksService.updateStatusBook(id, status);
        return "OK";
    }

    @PostMapping(value = "/api/bookInfo/{id}/addcomment")
    public String addComment(@RequestBody String textComment,
                             @PathVariable("id") long id) {
        commentsService.addNewCommentByIdBook(textComment, id);
        return "OK";
    }

    @PostMapping(value = "/api/bookInfo/{id}/deletebook")
    public String deleteBook(@PathVariable("id") long id) {
        booksService.deleteBookById(id);
        return "OK";
    }

    @PostMapping("/api/newBook")
    public String addNewBook(@RequestBody BookResponse book) {
        booksService.addNewBook(book.getNameBook(), book.getNameAuthor(), book.getNameGenre());
        return "OK";
    }
}
