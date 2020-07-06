package ru.otus.spring.homework_5.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.homework_5.domain.Authors;
import ru.otus.spring.homework_5.domain.Books;
import ru.otus.spring.homework_5.domain.Genres;
import ru.otus.spring.homework_5.service.BooksService;

@ShellComponent
public class ApplicationEventsCommands {

    BooksService booksService;
    Authors authors;
    Genres genres;
    Books books;

    ApplicationEventsCommands(BooksService booksService) {
        this.booksService = booksService;
    }

    @ShellMethod(value = "Select command", key = {"s", "select"})
    public String select() {
        booksService.getAllBooks();
        return "Показали весь список книг из библиотеки";
    }

    @ShellMethod(value = "Insert command. Example i 'new book' 1 1", key = {"i", "insert"})
    public String add(String nameBook, long idAuthor, long idGenres) {
        Books books = new Books(nameBook, new Authors(idAuthor), new Genres(idGenres));
        booksService.insertNewBook(books);
        return "Добавлена новая книга";
    }

    @ShellMethod(value = "Update command", key = {"u", "update"})
    public String update(long id, int status) {
        booksService.updateStatusBook(id, status);
        return "Изменили статус книги";
    }

    @ShellMethod(value = "Delete command", key = {"d", "delete"})
    public String update(long id) {
        booksService.deleteBookById(id);
        return "Удалили книгу";
    }
}
