package ru.otus.spring.homework11.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.annotation.DirtiesContext;
import reactor.test.StepVerifier;
import ru.otus.spring.homework11.models.Author;
import ru.otus.spring.homework11.models.Book;
import ru.otus.spring.homework11.models.Genre;

@DisplayName("Репозиторий для работы с книгами должен")
@DataMongoTest
public class BooksRepositoriesReactTest {

    @Autowired
    private BooksRepositoriesReact repoBooks;

    @DisplayName("найти книгу по её названию")
    @Test
    void shouldFindExpectedBookByName() {
        StepVerifier.create(
                repoBooks.findByName("Anna Karenina")
        )
                .expectNextCount(1)
                .expectComplete()
                .verify();
    }

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @DisplayName("загружать список книг")
    @Test
    void shouldReturnListBook() {
        StepVerifier.create(
                repoBooks.findAll()
        )
                .expectNextCount(4).expectComplete().verify();
    }

    @DisplayName("загружать информацию о нужной книге по его id")
    @Test
    void shouldFindExpectedBookById() {
        repoBooks.save(new Book("1", "Igrok",
                1, new Author("test author"),
                new Genre("new Genre"))).subscribe();

        StepVerifier.create(
                repoBooks.findById("1")
        )
                .expectNextCount(1)
                .expectComplete()
                .verify();
    }

    @DisplayName("добавить новую книгу")
    @Test
    public void shouldAddNewBookNotNull() {
        StepVerifier.create(
                repoBooks.save(new Book("3", "Igrok",
                        1, new Author("test author"),
                        new Genre("new Genre"))))
                .expectNextCount(1)
                .expectComplete()
                .verify();

        StepVerifier.create(
                repoBooks.findById("3")
        )
                .expectNextCount(1)
                .expectComplete()
                .verify();
    }

    @DisplayName("удалить книгу")
    @Test
    public void shouldDeleteBook() {
        Book book = new Book("2", "Igrok2",
                1, new Author("test author"),
                new Genre("new Genre"));

        repoBooks.save(book).subscribe();

        StepVerifier.create(
                repoBooks.findById("2")
        )
                .expectNextCount(1)
                .expectComplete()
                .verify();

        repoBooks.delete(book).subscribe();

        StepVerifier.create(
                repoBooks.findById("2")
        )
                .expectNextCount(0)
                .expectComplete()
                .verify();
    }
}