package ru.otus.spring.homework12.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.spring.homework12.models.Author;
import ru.otus.spring.homework12.models.Book;
import ru.otus.spring.homework12.models.Genre;
import ru.otus.spring.homework12.page.BooksController;
import ru.otus.spring.homework12.services.BooksServiceImpl;
import ru.otus.spring.homework12.services.CommentsService;
import ru.otus.spring.homework12.utils.TestUtil;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Тест REST контроллера BooksRestController ")
@WebMvcTest
public class BooksRestControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    BooksController controller;

    @MockBean
    BooksServiceImpl service;

    @MockBean
    CommentsService serviceComment;

    @DisplayName("Контекст успешно создаёт контроллер BooksRestController")
    @Test
    public void shouldCorrectCreateBooksController() {
        assertThat(controller).isNotNull();
    }

    @DisplayName("Возвращает json для списка книг. GET")
    @Test
    public void shouldReturnJsonListBooks() throws Exception {
        mvc.perform(get("/api/books")).andExpect(status().isOk());
    }

    @DisplayName("Возвращает json для информации по книге. GET")
    @Test
    public void shouldReturnJsonBookInfo() throws Exception {
        given(service.findBookById(2l)).willReturn(java.util.Optional.of(new Book("book", new Author(), new Genre())));
        mvc.perform(get("/api/bookInfo/" + 2l)).andExpect(status().isOk());
    }

    @DisplayName("Корректно работает добавление книги. POST")
    @Test
    public void shouldReturnJsonAddBook() throws Exception {
        BookResponse book = new BookResponse("test book", "test author", "test genre");
        mvc.perform(post("/api/newBook")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtil.toJson(book)).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    @DisplayName("Корректно работает Update статуса книги. POST")
    @Test
    public void shouldReturnJsonUpdateBook() throws Exception {
        String statusTest = "7";
        mvc.perform(post("/api/bookInfo/1/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtil.toJson(statusTest)).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    @DisplayName("Корректно работает удаление книги. POST")
    @Test
    public void shouldReturnJsonDeleteBook() throws Exception {
        mvc.perform(post("/api/bookInfo/3/deletebook")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    @DisplayName("Корректно работает добавление комментария к книге. POST")
    @Test
    public void shouldReturnJsonAddComment() throws Exception {
        String textCommentTest = "super book";
        mvc.perform(post("/api/bookInfo/1/addcomment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtil.toJson(textCommentTest))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

}

