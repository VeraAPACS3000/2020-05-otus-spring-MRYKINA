package ru.otus.spring.homework11.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.homework11.models.Author;
import ru.otus.spring.homework11.models.Book;
import ru.otus.spring.homework11.models.Genre;
import ru.otus.spring.homework11.repositories.BooksRepositoriesReact;

import static org.mockito.BDDMockito.given;

@DisplayName("Тест REST контроллера BooksRestController ")
@SpringBootTest
public class BooksRestControllerTest {

    @Autowired
    private BooksRestController restController;

    @MockBean
    private BooksRepositoriesReact repositoryBook;

    @DisplayName("Возвращает список книг. GET")
    @Test
    public void shouldReturnListBooks() {
        given(repositoryBook.findAll()).willReturn(Flux.just(new Book("Igrok",
                1, new Author("test author"),
                new Genre("new Genre"))));

        WebTestClient.bindToController(restController)
                .build()
                .get()
                .uri("/api/books")
                .exchange()
                .expectStatus().isOk();
    }

    @DisplayName("Возвращает информацию по книге. GET")
    @Test
    public void shouldReturnBookInfo() {
        given(repositoryBook.findById("1")).willReturn(Mono.just(new Book("Igrok",
                1, new Author("test author"),
                new Genre("new Genre"))));

        WebTestClient.bindToController(restController)
                .build()
                .get()
                .uri("/api/bookInfo/1")
                .exchange()
                .expectStatus().isOk();
    }

    @DisplayName("Корректно работает Update статуса книги. POST")
    @Test
    public void shouldReturnUpdateBook() throws Exception {
        Book book = new Book("1", "Igrok",
                1, new Author("test author"),
                new Genre("new Genre"));

        given(repositoryBook.save(book)).willReturn(Mono.just(new Book("1", "Igrok",
                1, new Author("test author"),
                new Genre("new Genre"))));

        given(repositoryBook.findById("1")).willReturn(Mono.just(book));

        WebTestClient.bindToController(restController)
                .build()
                .post()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/bookInfo/{id}/update")
                        .build(1))
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue("7")
                .exchange()
                .expectStatus().isOk();
    }

    @DisplayName("Корректно работает добавление книги. POST")
    @Test
    public void shouldReturnAddBook() {
        BookResponse bookResponse = new BookResponse();

        Book book = new Book("1", "Igrok",
                1, new Author("test author"),
                new Genre("new Genre"));

        given(repositoryBook.save(book)).willReturn(Mono.just(new Book("1", "Igrok",
                1, new Author("test author"),
                new Genre("new Genre"))));

        WebTestClient.bindToController(restController)
                .build()
                .post()
                .uri("/api/newBook")
                .contentType(MediaType.APPLICATION_STREAM_JSON)
                .bodyValue(bookResponse)
                .exchange().expectStatus().isOk();
    }

    @DisplayName("Корректно работает удаление книги. POST")
    @Test
    public void shouldReturnDeleteBook() {

        repositoryBook.insert(new Book("1", "test name book", 1, new Author("name test author"),
                new Genre("name test genre")));

        WebTestClient.bindToController(restController)
                .build()
                .post()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/bookInfo/{id}/deletebook")
                        .build(1))
                .exchange()
                .expectStatus().isOk();
    }
}

