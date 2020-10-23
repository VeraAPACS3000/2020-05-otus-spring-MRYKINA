package ru.otus.spring.homework11.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.event.annotation.BeforeTestMethod;
import org.springframework.test.web.reactive.server.WebTestClient;
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
    BooksRestController restController;

    @MockBean
    BooksRepositoriesReact repositoryBook;

    @DisplayName("Возвращает список книг. GET")
    @Test
    public void shouldReturnListBooks() {
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

    @BeforeTestMethod
    public void prepareTest() {
        repositoryBook.insert(new Book("1", "test name book", 1, new Author("name test author"),
                new Genre("name test genre")));
    }

    /*
            <415 UNSUPPORTED_MEDIA_TYPE>
     */
    @DisplayName("Корректно работает Update статуса книги. POST")
    @Test
    public void shouldReturnUpdateBook() throws Exception {
        given(repositoryBook.findById("1")).willReturn(Mono.just(new Book("Igrok",
                1, new Author("test author"),
                new Genre("new Genre"))));

        WebTestClient.bindToController(restController)
                .build()
                .post()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/bookInfo/{id}/update")
                        .build(1))
                .bodyValue("id")
                .exchange()
                .expectStatus().isOk();
    }

    @DisplayName("Корректно работает добавление книги. POST")
    @Test
    public void shouldReturnAddBook() {
        BookResponse book = new BookResponse("test book", "test author", "test genre");
        WebTestClient.bindToController(restController)
                .build()
                .post()
                .uri("/api/newBook")
                //.queryParam("book", book).build())
                // .contentType(MediaType.APPLICATION_STREAM_JSON)
                //.body(BodyInserters.fromValue( book))
                //.exchange().expectStatus().isOk();
                .bodyValue(book)
                .exchange().expectStatus().isOk();


       /* response = client.mutateWith(csrf()).post().uri("/login")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromFormData("book", book)
                .exchange();*/
    }

    /*
            <500 INTERNAL_SERVER_ERROR>
     */
    @DisplayName("Корректно работает удаление книги. POST")
    @Test
    public void shouldReturnDeleteBook() {
        /*given(repositoryBook.findById("1")).willReturn(Mono.just(new Book("Igrok",
                1, new Author("test author"),
                new Genre("new Genre"))));*/

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

