package ru.otus.spring.homework7.shell;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.Shell;
import org.springframework.test.annotation.DirtiesContext;
import ru.otus.spring.homework7.services.CommentsService;

import static org.assertj.core.api.Assertions.assertThat;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@DisplayName("Тестирование shell команд из CommentsLibraryShellCommandsTest")
@SpringBootTest
public class CommentsLibraryShellCommandsTest {

    @Autowired
    private Shell shell;

    @Autowired
    CommentsService commentsService;

    private static final String SELECT_COMMAND_COMMENT = "sc";
    private static final String RESULT_SELECT_COMMAND_COMMENT = "Показали все-все комментарии";
    private static final String FIND_BY_ID_COMMAND_COMMENT = "fcid";
    private static final String RESULT_FIND_BY_ID_COMMAND_COMMENT = "Нашли все комментарии к книге по id";
    private static final String FIND_BY_NAME_COMMAND_COMMENT = "fcn";
    private static final String FIND_COMMENT_NAME_BOOK = "Desperation";
    private static final String FIND_COMMENT_NAME_BOOK_NOT_FOUND = "newMisery";
    private static final String RESULT_FIND_BY_NAME_COMMAND_COMMENT = "Нашли все комментарии к книге по названию книги";
    private static final String RESULT_FIND_BY_NAME_COMMAND_COMMENT_NOT_FOUND = "Не нашли ни одного комментария к книге по названию книги";
    private static final String UPDATE_COMMAND_COMMENT = "uc";
    private static final String RESULT_UPDATE_COMMAND_COMMENT = "Изменили текст комментария";
    private static final String DELETE_COMMAND_COMMENT = "dc";
    private static final String RESULT_DELETE_COMMAND_COMMENT = "Удалили комментарий к книге по id";
    private static final String INSERT_COMMAND_COMMENT = "ic";
    private static final String RESULT_INSERT_COMMAND_COMMENT = "Добавлен новый комментарий";

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

    @DisplayName("Должен возвращать ответ: Не нашли ни одного комментария к книге по названию книги")
    @Test
    void shouldReturnCommentByNameBookNotFound() {
        String resultCommand = (String) shell.evaluate(() -> FIND_BY_NAME_COMMAND_COMMENT + " " + FIND_COMMENT_NAME_BOOK_NOT_FOUND);
        assertThat(resultCommand).isEqualTo(RESULT_FIND_BY_NAME_COMMAND_COMMENT_NOT_FOUND);
    }

    @DisplayName("Должен возвращать ответ: Нашли все комментарии к книге по названию книги")
    @Test
    void shouldReturnCommentByNameBook() {
        String resultCommand = (String) shell.evaluate(() -> FIND_BY_NAME_COMMAND_COMMENT + " " + FIND_COMMENT_NAME_BOOK);
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
