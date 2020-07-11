package ru.otus.spring.homework_5.dao.extractor;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.spring.homework_5.dao.BookDaoJdbc;
import ru.otus.spring.homework_5.domain.Books;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Тестирование маппера для возвращаемого обьекта книги")
@SpringBootTest
public class BookMapperTest {

    @Autowired
    BookDaoJdbc bookDaoJdbc;

    @DisplayName("Маппер должен вернуть книгу не null")
    @ParameterizedTest
    @MethodSource("generateDataReturnBookNotNull")
    public void testBookMapperTest(long id) {
        final Books actualBook = bookDaoJdbc.findById(id);
        assertThat(actualBook).isNotNull();
    }

    private static Stream<Arguments> generateDataReturnBookNotNull() {
        return Stream.of(
                Arguments.of(1),
                Arguments.of(2),
                Arguments.of(3),
                Arguments.of(4),
                Arguments.of(5)
        );
    }
}
