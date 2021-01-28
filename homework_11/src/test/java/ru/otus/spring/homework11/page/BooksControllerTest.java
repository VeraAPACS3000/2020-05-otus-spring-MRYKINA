package ru.otus.spring.homework11.page;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@DisplayName("Тест контроллера BooksController ")
@SpringBootTest
@AutoConfigureWebTestClient
public class BooksControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private BooksController controller;

    @DisplayName("Возвращает форму для списка книг. GET")
    @Test
    public void shouldReturnListBooks() {
        webTestClient
                .get()
                .uri("/")
                .exchange()
                .expectStatus().isOk();
    }

    @DisplayName("Возвращает форму для информации по книге. GET")
    @Test
    public void shouldReturnFormBookInfo() throws Exception {
        webTestClient
                .get()
                .uri("/bookInfo/1")
                .exchange()
                .expectStatus().isOk();
    }

    @DisplayName("Возвращает форму добавление книги. GET")
    @Test
    public void shouldReturnFormAddBook() throws Exception {
        webTestClient
                .get()
                .uri("/addNewBook")
                .exchange()
                .expectStatus().isOk();
    }
}
