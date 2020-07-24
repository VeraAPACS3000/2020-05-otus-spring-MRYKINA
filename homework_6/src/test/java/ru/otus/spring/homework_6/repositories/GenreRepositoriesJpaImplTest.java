package ru.otus.spring.homework_6.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import ru.otus.spring.homework_6.models.Genre;

import static org.assertj.core.api.Assertions.assertThat;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@DisplayName("Репозиторий для работы с жанрами должен")
@DataJpaTest
@Import(GenreRepositoriesJpaImpl.class)
public class GenreRepositoriesJpaImplTest {

    private static final String FIND_GENRE = "lyrics";

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    GenreRepositoriesJpa genreRepositoriesJpa;

    @DisplayName("находить жанр по названию")
    @Test
    void shouldFindExpectedBookById() {
        Genre actualGenre = genreRepositoriesJpa.findGenreByName(FIND_GENRE);
        long idGenre = actualGenre.getId();
        Genre expectedGenre = entityManager.find(Genre.class, idGenre);
        assertThat(actualGenre).isEqualToComparingFieldByField(expectedGenre);
    }

}
