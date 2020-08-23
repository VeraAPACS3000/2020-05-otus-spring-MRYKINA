package ru.otus.spring.homework8.shell;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.Shell;
import org.springframework.test.annotation.DirtiesContext;
import ru.otus.spring.homework8.services.CommentsServices;

import static org.assertj.core.api.Assertions.assertThat;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@DisplayName("Класс команд shell CommentsShellCommands должен ")
@SpringBootTest
public class CommentsShellCommandsTest {

    @Autowired
    private Shell shell;

    @Autowired
    CommentsServices commentsServices;

    private static final String SELECT_COMMAND_COMMENT = "sc";
    private static final String RESULT_SELECT_COMMAND_COMMENT = "Показали все комментарии";
    private static final String INSERT_COMMAND_COMMENT = "ac ";
    private static final String RESULT_INSERT_COMMAND_COMMENT = "Добавили комментарий в базу";
    private static final String TEXT_COMMENT_INSERT_COMMAND = "testNewComment";

    private static final String COUNT_COMMENT_COMMAND = "gac ";
    private static final String RETURN_COUNT_COMMENT_COMMAND = "Показали число комментариев в базе";

    @DisplayName("Должен возвращать ответ: Показали все комментарии")
    @Test
    void shouldReturnAllListComments() {
        String resultCommand = (String) shell.evaluate(() -> SELECT_COMMAND_COMMENT);
        assertThat(resultCommand).isEqualTo(RESULT_SELECT_COMMAND_COMMENT);
    }

    @DisplayName("Должен возвращать ответ: Добавлен новый комментарий")
    @Test
    void shouldInsertComment() {
        String resultCommand = (String) shell.evaluate(() -> INSERT_COMMAND_COMMENT +
                TEXT_COMMENT_INSERT_COMMAND);
        assertThat(resultCommand).isEqualTo(RESULT_INSERT_COMMAND_COMMENT);
    }

    @DisplayName("Должен возвращать ответ: Показали число комментариев в базе")
    @Test
    void shouldGetCountCommentsInBD() {
        String resultCommand = (String) shell.evaluate(() -> COUNT_COMMENT_COMMAND);
        assertThat(resultCommand).isEqualTo(RETURN_COUNT_COMMENT_COMMAND);
    }
}
