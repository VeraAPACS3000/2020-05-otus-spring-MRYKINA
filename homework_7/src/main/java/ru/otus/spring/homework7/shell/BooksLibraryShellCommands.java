package ru.otus.spring.homework7.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.homework7.models.Book;
import ru.otus.spring.homework7.services.BooksService;

import java.util.List;
import java.util.Optional;


@ShellComponent
public class BooksLibraryShellCommands {

    BooksService booksService;


    BooksLibraryShellCommands(BooksService booksService) {
        this.booksService = booksService;
    }

    @ShellMethod(value = "Select all books", key = {"sb", "selectBooks"})
    public String selectAllBooks() {
        List<Book> bookList = booksService.getAllBooks();
        System.out.println(bookList);
        return "Показали весь список книг из библиотеки";
    }

    @ShellMethod(value = "Insert new book. Example ib 'new book' 'Lermontov' 'prose'", key = {"ib", "insertBook"})
    public String addNewBook(String nameBook, String nameAuthor, String nameGenre) {
        booksService.addNewBook(nameBook, nameAuthor, nameGenre);
        return "Добавлена новая книга";
    }

    @ShellMethod(value = "Update book's status", key = {"ub", "updateBook"})
    public String update(long id, int status) {
        booksService.updateStatusBook(id, status);
        return "Изменили статус книги";
    }

    @ShellMethod(value = "Delete book", key = {"db", "deleteBook"})
    public String deleteBook(long id) {
        booksService.deleteBookById(id);
        return "Удалили книгу";
    }

    @ShellMethod(value = "Find book by Id. Example: fbid 1", key = {"fbid", "findBookById"})
    public String findBookById(long id) {
        Optional<Book> book = booksService.findBookById(id);
        System.out.println(book.isPresent() ? book.get() : "");
        return "Нашли книгу по id";
    }

    @ShellMethod(value = "Find book by name. Example: fbname 'Misery'", key = {"fbname", "findBookByName"})
    public String findBookByName(String name) {
        Optional<Book> book = booksService.findBookByName(name);
        System.out.println(book.isPresent() ? book.get() : "");
        return "Нашли книгу по названию";
    }
}
