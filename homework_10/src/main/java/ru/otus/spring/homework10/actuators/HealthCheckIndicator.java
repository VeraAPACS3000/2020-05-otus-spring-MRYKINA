package ru.otus.spring.homework10.actuators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;
import ru.otus.spring.homework10.models.Book;
import ru.otus.spring.homework10.services.BooksService;

import java.util.List;

@Component("myHealth")
public class HealthCheckIndicator implements HealthIndicator {

    @Autowired
    BooksService booksService;

    @Override
    public Health health() {
        final List<Book> booksList = booksService.getAllBooks();

        if (booksList == null) {
            return Health.
                    status(Status.UNKNOWN)
                    .withDetail("message", "Нет книг")
                    .build();
        } else if (booksList.size() == 0) {
            return Health.
                    status(Status.DOWN)
                    .withDetail("message", "Книги не загрузились")
                    .withDetail("error", "null")
                    .build();
        } else {
            return Health.
                    status(Status.UP)
                    .withDetail("message", "Загрузилось " + booksList.size() + " книг")
                    .build();
        }
    }
}
