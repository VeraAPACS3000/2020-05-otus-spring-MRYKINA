package ru.otus.spring.homework_5.dao.extractor;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.spring.homework_5.dao.BookDaoJdbc;
import ru.otus.spring.homework_5.domain.Books;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Тестирование ResultSetExtractor для возвращаемого списка книг")
@SpringBootTest
public class BookResultSetExtractorTest {

    @Autowired
    BookDaoJdbc bookDaoJdbc;

    @DisplayName("Список должен вернуться не null")
    @Test
    void testReturnListNotNull() {
        final List<Books> booksList = bookDaoJdbc.getAll();
        assertThat(booksList).isNotNull();
    }
}
