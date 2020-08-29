package ru.otus.spring.homework8.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.homework8.model.Book;
import ru.otus.spring.homework8.services.BooksServices;

import java.util.List;

@ShellComponent
public class BooksShellCommands {

    BooksServices booksServices;

    BooksShellCommands(BooksServices booksServices) {
        this.booksServices = booksServices;
    }

    @ShellMethod(value = "Select all books", key = {"sb", "selectBooks"})
    public String selectAllBooks() {
        List<Book> bookList = booksServices.findAll();
        System.out.println(bookList);
        return "Показали весь список книг из библиотеки";
    }

    @ShellMethod(value = "Find book by name. Example: fbname 'Besi'", key = {"fbname", "findBookByName"})
    public String findBookByName(String name) {
        Book book = booksServices.findByName(name);
        System.out.println(book);
        return "Нашли книгу по названию";
    }

    @ShellMethod(value = "Delete book", key = {"db", "deleteBook"})
    public String deleteBook(String nameBook) {
        booksServices.deleteByNameBook(nameBook);
        return "Удалили книгу";
    }

    @ShellMethod(value = "Insert new book. Example ib 'new book' 'Lermontov' 'prose'", key = {"ib", "insertBook"})
    public String addNewBook(String nameBook, String nameAuthor, String nameGenre) {
        booksServices.add(nameBook, nameAuthor, nameGenre);
        return "Добавлена новая книга";
    }

    @ShellMethod(value = "Update book's status", key = {"ub", "updateBook"})
    public String update(String nameBook, int status) {
        booksServices.update(nameBook, status);
        return "Изменили статус книги";
    }

    @ShellMethod(value = "Get count comments about book", key = {"gc", "getCountComments"})
    public String getCountComments(String nameBook) {
        booksServices.getCountCommentsAboutBook(nameBook);
        return "Вернули кол-во комментариев у книги:" + nameBook;
    }
}
