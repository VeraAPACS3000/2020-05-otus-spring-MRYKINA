package ru.otus.spring.homework_7.shell;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.Shell;
import org.springframework.test.annotation.DirtiesContext;
import ru.otus.spring.homework_7.services.BooksService;
import ru.otus.spring.homework_7.services.CommentsService;

import static org.assertj.core.api.Assertions.assertThat;


@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@DisplayName("Тестирование shell команд из LibraryBooksShellCommandsTest")
@SpringBootTest
public class LibraryBooksShellCommandsTest {

    @Autowired
    private Shell shell;

    @Autowired
    BooksService booksService;

    @Autowired
    CommentsService commentsService;

    //Books
    private static final String SELECT_COMMAND_BOOK = "sb";
    private static final String RESULT_SELECT_COMMAND_BOOK = "Показали весь список книг из библиотеки";
    private static final String INSERT_COMMAND_BOOK = "ib";
    private static final String RESULT_INSERT_COMMAND_BOOK = "Добавлена новая книга";
    private static final String UPDATE_COMMAND_BOOK = "ub";
    private static final String RESULT_UPDATE_COMMAND_BOOK = "Изменили статус книги";
    private static final String DELETE_COMMAND_BOOK = "db";
    private static final String RESULT_DELETE_COMMAND_BOOK = "Удалили книгу";
    private static final String FIND_BY_ID_COMMAND_BOOK = "fbid";
    private static final String RESULT_FIND_BY_ID_COMMAND_BOOK = "Нашли книгу по id";
    private static final String FIND_BY_NAME_COMMAND_BOOK = "fbname";
    private static final String RESULT_FIND_BY_NAME_COMMAND_BOOK = "Нашли книгу по названию";

    //Comments
    private static final String SELECT_COMMAND_COMMENT = "sc";
    private static final String RESULT_SELECT_COMMAND_COMMENT = "Показали все-все комментарии";
    private static final String FIND_BY_ID_COMMAND_COMMENT = "fcid";
    private static final String RESULT_FIND_BY_ID_COMMAND_COMMENT = "Нашли все комментарии к книге по id";
    private static final String FIND_BY_NAME_COMMAND_COMMENT = "fcn";
    private static final String RESULT_FIND_BY_NAME_COMMAND_COMMENT = "Нашли все комментарии к книге по названию книги";
    private static final String UPDATE_COMMAND_COMMENT = "uc";
    private static final String RESULT_UPDATE_COMMAND_COMMENT = "Изменили текст комментария";
    private static final String DELETE_COMMAND_COMMENT = "dc";
    private static final String RESULT_DELETE_COMMAND_COMMENT = "Удалили комментарий к книге по id";
    private static final String INSERT_COMMAND_COMMENT = "ic";
    private static final String RESULT_INSERT_COMMAND_COMMENT = "Добавлен новый комментарий";


    @DisplayName("Должен возвращать ответ: Показали весь список книг из библиотеки")
    @Test
    void shouldReturnAllListBooks() {
        String resultCommand = (String) shell.evaluate(() -> SELECT_COMMAND_BOOK);
        assertThat(resultCommand).isEqualTo(RESULT_SELECT_COMMAND_BOOK);
    }

    @DisplayName("Должен возвращать ответ: Добавлена новая книга")
    @Test
    void shouldInsertBook() {
        String resultCommand = (String) shell.evaluate(() -> INSERT_COMMAND_BOOK + " newBook 'newAuthor' 'newGenre'");
        assertThat(resultCommand).isEqualTo(RESULT_INSERT_COMMAND_BOOK);
    }

    @DisplayName("Должен возвращать ответ: Изменили статус книги")
    @Test
    void shouldUpdateBook() {
        String resultCommand = (String) shell.evaluate(() -> UPDATE_COMMAND_BOOK + " 1 0");
        assertThat(resultCommand).isEqualTo(RESULT_UPDATE_COMMAND_BOOK);
    }

    @DisplayName("Должен возвращать ответ: Удалили книгу")
    @Test
    void shouldDeleteBook() {
        String resultCommand = (String) shell.evaluate(() -> DELETE_COMMAND_BOOK + " 1");
        assertThat(resultCommand).isEqualTo(RESULT_DELETE_COMMAND_BOOK);
    }

    @DisplayName("Должен возвращать ответ: Нашли книгу по id")
    @Test
    void shouldFindBookById() {
        String resultCommand = (String) shell.evaluate(() -> FIND_BY_ID_COMMAND_BOOK + " 3");
        assertThat(resultCommand).isEqualTo(RESULT_FIND_BY_ID_COMMAND_BOOK);
    }

    @DisplayName("Должен возвращать ответ: Нашли книгу по названию")
    @Test
    void shouldFindBookByName() {
        String resultCommand = (String) shell.evaluate(() -> FIND_BY_NAME_COMMAND_BOOK + " Misery");
        assertThat(resultCommand).isEqualTo(RESULT_FIND_BY_NAME_COMMAND_BOOK);
    }

    //Comments
    @DisplayName("Должен возвращать ответ: Показали все-все комментарии")
    @Test
    void shouldReturnAllListComments() {
        String resultCommand = (String) shell.evaluate(() -> SELECT_COMMAND_COMMENT);
        assertThat(resultCommand).isEqualTo(RESULT_SELECT_COMMAND_COMMENT);
    }

    @DisplayName("Должен возвращать ответ: Нашли все комментарии к книге по id")
    @Test
    void shouldReturnCommentByIdBook() {
        String resultCommand = (String) shell.evaluate(() -> FIND_BY_ID_COMMAND_COMMENT + " 2");
        assertThat(resultCommand).isEqualTo(RESULT_FIND_BY_ID_COMMAND_COMMENT);
    }

    @DisplayName("Должен возвращать ответ: Нашли все комментарии к книге по названию книги")
    @Test
    void shouldReturnCommentByNameBook() {
        String resultCommand = (String) shell.evaluate(() -> FIND_BY_NAME_COMMAND_COMMENT + " 2");
        assertThat(resultCommand).isEqualTo(RESULT_FIND_BY_NAME_COMMAND_COMMENT);
    }

    @DisplayName("Должен возвращать ответ: Изменили текст комментария")
    @Test
    void shouldUpdateComment() {
        String resultCommand = (String) shell.evaluate(() -> UPDATE_COMMAND_COMMENT + " 5 new_comment");
        assertThat(resultCommand).isEqualTo(RESULT_UPDATE_COMMAND_COMMENT);
    }

    @DisplayName("Должен возвращать ответ: Удалили комментарий к книге по id")
    @Test
    void shouldDeleteComment() {
        String resultCommand = (String) shell.evaluate(() -> DELETE_COMMAND_COMMENT + " 5");
        assertThat(resultCommand).isEqualTo(RESULT_DELETE_COMMAND_COMMENT);
    }

    @DisplayName("Должен возвращать ответ: Добавлен новый комментарий")
    @Test
    void shouldInsertComment() {
        String resultCommand = (String) shell.evaluate(() -> INSERT_COMMAND_COMMENT + " newComment Desperation");
        assertThat(resultCommand).isEqualTo(RESULT_INSERT_COMMAND_COMMENT);
    }
}
