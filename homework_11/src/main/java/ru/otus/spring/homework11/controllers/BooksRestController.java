package ru.otus.spring.homework11.controllers;

import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.homework11.dto.BookDto;
import ru.otus.spring.homework11.models.Author;
import ru.otus.spring.homework11.models.Book;
import ru.otus.spring.homework11.models.Comment;
import ru.otus.spring.homework11.models.Genre;
import ru.otus.spring.homework11.repositories.AuthorsRepositoriesReact;
import ru.otus.spring.homework11.repositories.BooksRepositoriesReact;
import ru.otus.spring.homework11.repositories.CommentsRepositpriesReact;

import java.util.List;

@RestController
public class BooksRestController {
    private final BooksRepositoriesReact repositoryBook;
    private final CommentsRepositpriesReact repositoryComment;
    private final AuthorsRepositoriesReact repositoriesAuthors;
    private final ReactiveMongoTemplate template;

    public BooksRestController(BooksRepositoriesReact repositoryBook, CommentsRepositpriesReact repositoryComment, AuthorsRepositoriesReact repositoriesAuthors, ReactiveMongoTemplate template) {
        this.repositoryBook = repositoryBook;
        this.repositoryComment = repositoryComment;
        this.repositoriesAuthors = repositoriesAuthors;
        this.template = template;
    }

    @GetMapping("/api/books")
    public Flux<BookDto> getAllBooks() {
        System.out.println("call getAllBooks");
        return Flux.from(repositoryBook.findAll().map(BookDto::toDto));
    }

    @GetMapping("/api/bookInfo/{id}")
    public Mono<BookDto> getBookById(@PathVariable String id) {
        return Mono.from(repositoryBook.findById(id).map(BookDto::toDto)).defaultIfEmpty(
                new BookDto("255", "Fake", 1, "Fake", "Fake", List.of(
                        new Comment("new comment 123")
                ))
        );
    }

    @PostMapping("/api/newBook")
    public Mono addNewBook(@RequestBody BookResponse book) {
        return Mono.from(repositoryBook.insert(new Book(book.getNameBook(), 1, new Author(book.getNameAuthor()),
                new Genre(book.getNameGenre())))
        );
    }

    @PostMapping(value = "/api/bookInfo/{id}/deletebook")
    public Mono deleteBook(@PathVariable("id") String id) {
        return Mono.from(repositoryBook.deleteById(id));
    }

    @PostMapping(value = "/api/bookInfo/{id}/update")
    public Mono updateStatusBook(@RequestBody int status,
                                 @PathVariable("id") String id) {
        return Mono.from(repositoryBook.findById(id).flatMap(book -> {
            book.setStatus(status);
            return repositoryBook.save(book).onErrorResume(throwable ->
                    Mono.from(repositoryBook.findById(id)).log());
        }));
    }

    @PostMapping(value = "/api/bookInfo/{id}/addcomment")
    public Mono addComment(@RequestBody String textComment,
                           @PathVariable("id") String id) {
        return Mono.from(repositoryBook.findById(id).flatMap(book -> {
            book.getComments().add(new Comment(textComment));
            return repositoryBook.save(book).onErrorResume(throwable ->
                    Mono.from(repositoryBook.findById(id)).log());
        }));

    }
}