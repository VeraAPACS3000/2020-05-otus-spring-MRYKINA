package ru.otus.spring.homework11.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Mono;
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
    public Mono<String> listAllBooks() {
        System.out.println("test-test");
        return Mono.just("listBooks").log();
    }

    /*@GetMapping("/bookInfo/{id}")
    public String getBookById(@PathVariable("id") long id, Model model) {
        model.addAttribute("id", id);
        return "details";
    }

    @GetMapping("/addNewBook")
    public String addBook() {
        return "newBook";
    }*/

}
