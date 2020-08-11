package ru.otus.spring.homework7.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;
import ru.otus.spring.homework7.models.Author;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@DisplayName("Репозиторий для работы с авторами должен")
@DataJpaTest
public class AuthorRepositoriesJpaTest {
    private static final String FIND_AUTHOR = "Steven King";

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    AuthorRepositoriesJpa repoAuthor;

    @DisplayName("загружать автора по имени")
    @Test
    void shouldFindExpectedBookById() {
        Optional<Author> actualAuthor = repoAuthor.findByName(FIND_AUTHOR);
        long idAuthor = actualAuthor.get().getId();
        Author expectedAuthor = entityManager.find(Author.class, idAuthor);
        assertThat(actualAuthor).isPresent().get().isEqualToComparingFieldByField(expectedAuthor);
    }
}
