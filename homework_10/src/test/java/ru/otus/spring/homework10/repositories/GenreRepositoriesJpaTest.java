package ru.otus.spring.homework10.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;
import ru.otus.spring.homework10.models.Genre;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@DisplayName("Репозиторий для работы с жанрами должен")
@DataJpaTest
public class GenreRepositoriesJpaTest {
    private static final String FIND_GENRE = "lyrics";

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    GenreRepositoriesJpa genreRepositoriesJpa;

    @DisplayName("загружать жанр по названию")
    @Test
    void shouldFindExpectedBookById() {
        Optional<Genre> actualGenre = genreRepositoriesJpa.findByName(FIND_GENRE);
        long idGenre = actualGenre.get().getId();
        Genre expectedGenre = entityManager.find(Genre.class, idGenre);
        assertThat(actualGenre).isPresent().get().isEqualToComparingFieldByField(expectedGenre);
    }
}
