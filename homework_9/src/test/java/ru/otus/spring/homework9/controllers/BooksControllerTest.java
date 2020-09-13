package ru.otus.spring.homework9.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.spring.homework9.models.Author;
import ru.otus.spring.homework9.models.Book;
import ru.otus.spring.homework9.models.Comment;
import ru.otus.spring.homework9.models.Genre;
import ru.otus.spring.homework9.services.BooksServiceImpl;
import ru.otus.spring.homework9.services.CommentsService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("Тест контроллера BooksController ")
@WebMvcTest
public class BooksControllerTest {

    private static String NAME_TEST_BOOK = "book test";
    private static String NAME_TEST_AUTHOR = "book author";
    private static String NAME_TEST_GENRE = "book genre";

    @Autowired
    MockMvc mvc;

    @Autowired
    BooksController controller;

    @MockBean
    BooksServiceImpl service;

    @MockBean
    CommentsService serviceComment;

    @DisplayName("Контекст успешно создаёт контроллер BooksController")
    @Test
    public void shouldCorrectCreateBooksController() {
        assertThat(controller).isNotNull();
    }

    @DisplayName("Возвращает форму для списка книг. GET")
    @Test
    public void shouldReturnFormListBooks() throws Exception {
        mvc.perform(get("/")).andExpect(status().isOk());
    }

    @DisplayName("Список книг содержит заданную книгу. GET")
    @Test
    public void shouldContainCorrectBook() throws Exception {
        mvc.perform(get("/")).andExpect(status().isOk()).andExpect(content().string(containsString("book test")));
    }

    @DisplayName("Возвращает форму для информации по 1 книге. GET")
    @Test
    public void shouldReturnFormInfoOneBook() throws Exception {
        mvc.perform(get("/bookInfo?id=1")).andExpect(status().isOk());
    }

    @DisplayName("В информации по книге содержится заданная книга. GET")
    @Test
    public void shouldContainCorrectInfoBook() throws Exception {
        mvc.perform(get("/bookInfo?id=1")).
                andExpect(status().isOk()).
                andExpect(content().string(containsString(NAME_TEST_BOOK))).
                andExpect(content().string(containsString(NAME_TEST_AUTHOR))).
                andExpect(content().string(containsString(NAME_TEST_GENRE)));
    }

    @DisplayName("Возвращает форму для добавления книги. GET")
    @Test
    public void shouldReturnFormAddBook() throws Exception {
        mvc.perform(get("/newBook")).andExpect(status().isOk());
    }

    @DisplayName("После обновления книги происходит редирект на корректную форму и отрабатывает вызов сервиса. POST")
    @Test
    public void shouldRedirectAfterUpdateStatusBook() throws Exception {
        mvc.perform(post("/bookInfo")
                .param("action", "update")
                .param("id", "1")
                .param("status", "7")
                .param("textComment", "text comment test post")).
                andExpect(redirectedUrlTemplate("/bookInfo?id=1"));

        verify(service, times(1)).updateStatusBook(1l, 7);
    }

    @DisplayName("После удаления книги происходит редирект на корректную форму и отрабатывает вызов сервиса. POST")
    @Test
    public void shouldRedirectAfterDeleteStatusBook() throws Exception {
        mvc.perform(post("/bookInfo")
                .param("action", "delete")
                .param("id", "1")
                .param("status", "7")
                .param("textComment", "text comment test post"))
                .andExpect(redirectedUrlTemplate("/"));

        verify(service, times(1)).deleteBookById(1);
    }

    @DisplayName("После добавления комментария происходит редирект на корректную форму и" +
            "отрабатывает вызов сервиса. POST")
    @Test
    public void shouldRedirectAfterAddedComment() throws Exception {
        mvc.perform(post("/bookInfo")
                .param("action", "comment")
                .param("id", "1")
                .param("status", "7")
                .param("textComment", "text comment test post")).
                andExpect(redirectedUrlTemplate("/bookInfo?id=1"));

        verify(serviceComment, times(1))
                .addNewCommentByIdBook("text comment test post", 1);
    }

    @BeforeEach
    void prepareDataAllTest() {
        List<Book> listBook = new ArrayList<>();
        List<Comment> commentList = new ArrayList<>();
        Comment comment = new Comment(1, "test comment");
        commentList.add(comment);
        Book book = new Book(1, NAME_TEST_BOOK, 1, new Author(1, NAME_TEST_AUTHOR),
                new Genre(1, NAME_TEST_GENRE), commentList);
        listBook.add(book);
        given(service.getAllBooks()).willReturn(listBook);
        given(service.findBookById(1)).willReturn(Optional.of(book));
    }
}

