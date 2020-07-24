package ru.otus.spring.homework_6.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import ru.otus.spring.homework_6.models.Author;

import static org.assertj.core.api.Assertions.assertThat;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@DisplayName("Репозиторий для работы с авторами должен")
@DataJpaTest
@Import(AuthorRepositoriesJpaImpl.class)
public class AuthorRepositoriesJpaImplTest {

    private static final String FIND_AUTHOR = "Steven King";

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    AuthorRepositoriesJpaImpl authorRepositoriesJpa;

    @DisplayName("находить автора по имени")
    @Test
    void shouldFindExpectedBookById() {
        Author actualAuthor = authorRepositoriesJpa.findAuthorByName(FIND_AUTHOR);
        long idAuthor = actualAuthor.getId();
        Author expectedAuthor = entityManager.find(Author.class, idAuthor);
        assertThat(actualAuthor).isEqualToComparingFieldByField(expectedAuthor);
    }
}
