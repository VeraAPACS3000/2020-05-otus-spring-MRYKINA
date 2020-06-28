package ru.otus.spring.homework_4.shell;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.shell.Shell;
import ru.otus.spring.homework_4.service.QuizService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@DisplayName("Тест команды shell")
@SpringBootTest
public class ApplicationEventsCommandsTest {

    @MockBean
    QuizService quizService;//очень интересно работает тест shell.
    //моку не нужно задавать поведение,а ведь в вызове сервиса runQuizPerson()
    //целая программа. Походу тест shell смотрит только на работу команд shell.
    //при дебаге видно

    @Autowired
    Shell shell;

    private static final String COMMAND_START = "s";
    private static final String COMMAND_START_SHORT = "start";
    private static final String RESULT_COMMAND_START = "Test finished";

    @DisplayName("Должен запускать сервис quiz-тестирования студента")
    @Test
    void shouldRunServiceRunQuizPerson() {
        String resultCommandShell = (String) shell.evaluate(() -> COMMAND_START);
        assertThat(resultCommandShell).isEqualTo(RESULT_COMMAND_START);
        verify(quizService, times(1)).runQuizPerson();//количество вызовов
    }

    @DisplayName("Должен запускать сервис quiz-тестирования студента. Короткая команда")
    @Test
    void shouldRunServiceRunQuizPersonShortCommand() {
        String resultCommandShell = (String) shell.evaluate(() -> COMMAND_START_SHORT);
        assertThat(resultCommandShell).isEqualTo(RESULT_COMMAND_START);
        verify(quizService, times(1)).runQuizPerson();
    }
}
