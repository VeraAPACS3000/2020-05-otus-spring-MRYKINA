package ru.otus.spring.homework_6.repositories.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import ru.otus.spring.homework_6.models.Author;
import ru.otus.spring.homework_6.services.AuthorService;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@DisplayName("Тестирование сервиса для работы с авторами")
@SpringBootTest
public class AuthorServiceImplTest {

    private static final String SELECT_NAME_AUTHOR = "Steven King";

    @Autowired
    AuthorService authorService;

    @Autowired
    EntityManager entityManager;

    @DisplayName("Должен вернуть автора по его имени")
    @Test
    void shouldReturnListBooks() {
        final Author author = authorService.getAuthorByName(SELECT_NAME_AUTHOR);
        assertThat(author).isNotNull();
    }

}
