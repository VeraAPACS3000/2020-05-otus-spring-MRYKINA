package ru.otus.spring.homework_4.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.homework_4.service.QuizService;

@ShellComponent
public class ApplicationEventsCommands {

    private final QuizService quizService;

    // @Autowired - с SpringBoot уже не требуется использовать @Autowired
    //сам подтащит
    ApplicationEventsCommands(QuizService quizService) {
        this.quizService = quizService;
    }

    @ShellMethod(value = "Start command", key = {"s", "start"})
    public String start() {
        quizService.runQuizPerson();
        return "Test finished";
    }
}
