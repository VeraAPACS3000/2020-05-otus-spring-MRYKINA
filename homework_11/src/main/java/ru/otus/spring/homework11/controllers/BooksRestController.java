package ru.otus.spring.homework11.controllers;

import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.homework11.dto.BookDto;
import ru.otus.spring.homework11.models.Book;
import ru.otus.spring.homework11.models.Comment;
import ru.otus.spring.homework11.repositories.BooksRepositoriesReact;
import ru.otus.spring.homework11.repositories.CommentsRepositpriesReact;

import java.util.List;

@RestController
public class BooksRestController {

    private final BooksRepositoriesReact repositoryBook;
    private final CommentsRepositpriesReact repositoryComment;
    private final ReactiveMongoTemplate template;

    public BooksRestController(BooksRepositoriesReact repositoryBook, CommentsRepositpriesReact repositoryComment, ReactiveMongoTemplate template) {
        this.repositoryBook = repositoryBook;
        this.repositoryComment = repositoryComment;
        this.template = template;
    }

    @GetMapping("/api/books")
    public Flux<BookDto> getAllBooks() {
               return Flux.from(repositoryBook.findAll().map(BookDto::toDto));
    }

    @GetMapping("/api/bookInfo/{id}")
    public Mono<BookDto> getBookById(@PathVariable String id) {
        return Mono.from(repositoryBook.findByName(id).map(BookDto::toDto)).defaultIfEmpty(
                new BookDto("255", "test db", 1, "name1", "name2", List.of(
                        new Comment("new comment 123")
                ))
        );
    }

    //TODO:при реализации view делаешь поиск по id
    @PostMapping(value = "/api/bookInfo/{id}/update/{status}")
    public Mono<String> detailsBook(@PathVariable("status") int status,
                              @PathVariable("name") String name) {
        Query query = new Query(Criteria.where("name").is(name));
        Update update = new Update().set("status", status);
        template.findAndModify(query, update, Book.class);
        //template.save(book, "books");
        return Mono.just("OK");
    }

   /* @PostMapping(value = "/api/bookInfo/{id}/addcomment")
    public String addComment(@RequestBody String textComment,
                             @PathVariable("id") long id) {
        Comment comment = new Comment(textComment);
        repositoryComment.insert(comment);
        commentsService.addNewCommentByIdBook(textComment, id);
        return "OK";
    }*/

    /*
    List<Book> listBooks = serviceBook.findAll();
        Book book = listBooks.get(0);
        Optional<Book> bookFind = serviceBook.findById(book.getId());
     */

}
