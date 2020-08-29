package ru.otus.spring.homework8.shell;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.Shell;
import org.springframework.test.annotation.DirtiesContext;
import ru.otus.spring.homework8.services.BooksServices;

import static org.assertj.core.api.Assertions.assertThat;


@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@DisplayName("Класс команд shell BooksShellCommands должен ")
@SpringBootTest
public class BooksShellCommandsTest {

    @Autowired
    private Shell shell;

    @Autowired
    BooksServices booksService;

    //select commands
    private static final String SELECT_COMMAND_BOOK = "sb";
    private static final String RESULT_SELECT_COMMAND_BOOK = "Показали весь список книг из библиотеки";

    //find commands
    private static final String FIND_BY_NAME_COMMAND_BOOK = "fbname ";
    private static final String NAME_BOOK = "IgrokNewTEST";
    private static final String RESULT_FIND_BY_NAME_COMMAND_BOOK = "Нашли книгу по названию";

    //get commands
    private static final String GET_COUNT_COMMENTS_COMMAND = "gc ";
    private static final String NAME_BOOK_GET_COUNT_COMMAND = "IgrokNewTEST";
    private static final String RESULT_GET_COUNT_COMMENTS_COMMAND = "Вернули кол-во комментариев у книги:";

    //insert commands
    private static final String INSERT_COMMAND_BOOK = "ib ";
    private static final String RESULT_INSERT_COMMAND_BOOK = "Добавлена новая книга";
    private static final String NAME_BOOK_INSERT_COMMAND = "newTestBook ";
    private static final String NAME_AUTHOR_INSERT_COMMAND = "newTestAuthor ";
    private static final String NAME_GENRE_INSERT_COMMAND = "newTestGenre ";

    //update commands
    private static final String UPDATE_COMMAND_BOOK = "ub ";
    private static final String RESULT_UPDATE_COMMAND_BOOK = "Изменили статус книги";
    private static final String NAME_BOOK_UPDATE_COMMAND = "VojnaIMirTEST ";
    private static final int STATUS_BOOK_UPDATE_COMMAND = 7;

    //delete commands
    private static final String DELETE_COMMAND_BOOK = "db ";
    private static final String RESULT_DELETE_COMMAND_BOOK = "Удалили книгу";
    private static final String NAME_BOOK_DELETE_COMMAND = "AnnaKareninaTEST";


    @DisplayName("возвращать ответ: Показали весь список книг из библиотеки")
    @Test
    void shouldReturnAllListBooks() {
        String resultCommand = (String) shell.evaluate(() -> SELECT_COMMAND_BOOK);
        assertThat(resultCommand).isEqualTo(RESULT_SELECT_COMMAND_BOOK);
    }

    @DisplayName("возвращать ответ: Нашли книгу по названию")
    @Test
    void shouldFindBookByName() {
        String resultCommand = (String) shell.evaluate(() -> FIND_BY_NAME_COMMAND_BOOK + NAME_BOOK);
        assertThat(resultCommand).isEqualTo(RESULT_FIND_BY_NAME_COMMAND_BOOK);
    }

    @DisplayName("Должен возвращать ответ: Удалили книгу")
    @Test
    void shouldDeleteBook() {
        String resultCommand = (String) shell.evaluate(() -> DELETE_COMMAND_BOOK + NAME_BOOK_DELETE_COMMAND);
        assertThat(resultCommand).isEqualTo(RESULT_DELETE_COMMAND_BOOK);
    }

    @DisplayName("Должен возвращать ответ: Добавлена новая книга")
    @Test
    void shouldInsertBook() {
        String resultCommand = (String) shell.evaluate(() -> INSERT_COMMAND_BOOK
                + NAME_BOOK_INSERT_COMMAND + NAME_AUTHOR_INSERT_COMMAND + NAME_GENRE_INSERT_COMMAND);
        assertThat(resultCommand).isEqualTo(RESULT_INSERT_COMMAND_BOOK);
    }

    @DisplayName("Должен возвращать ответ: Изменили статус книги")
    @Test
    void shouldUpdateBook() {
        String resultCommand = (String) shell.evaluate(() -> UPDATE_COMMAND_BOOK + NAME_BOOK_UPDATE_COMMAND
                + STATUS_BOOK_UPDATE_COMMAND);
        assertThat(resultCommand).isEqualTo(RESULT_UPDATE_COMMAND_BOOK);
    }

    @DisplayName("Должен возвращать ответ: Вернули кол-во комментариев у книги:[название книги]")
    @Test
    void shouldReturnCountCommentsInBook() {
        String resultCommand = (String) shell.evaluate(() -> GET_COUNT_COMMENTS_COMMAND + NAME_BOOK_GET_COUNT_COMMAND);
        assertThat(resultCommand).isEqualTo(RESULT_GET_COUNT_COMMENTS_COMMAND + NAME_BOOK_GET_COUNT_COMMAND);
    }
}
