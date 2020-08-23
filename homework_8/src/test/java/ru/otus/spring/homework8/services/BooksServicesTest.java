package ru.otus.spring.homework8.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import ru.otus.spring.homework8.model.Book;
import ru.otus.spring.homework8.model.Comment;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@SpringBootTest
@DisplayName("BooksServices должен ")
public class BooksServicesTest {

    private static int UPDATE_STATUS_BOOK = 7;
    private static String FIND_NAME_BOOK = "Vojna i mir TEST";

    @Autowired
    BooksServices serviceBook;

    @DisplayName("возвращать список книг. Не Null")
    @Test
    void shouldReturnListBook() {
        List<Book> listBooks = serviceBook.findAll();
        System.out.println(listBooks);
        assertThat(listBooks).isNotNull();
    }

    @DisplayName("возвращать книгу по имени")
    @Test
    void shouldReturnBookByName() {
        Book book = serviceBook.findByName(FIND_NAME_BOOK);
        assertThat(book.getName()).isEqualTo(FIND_NAME_BOOK);
    }

    @DisplayName("возвращать книгу по id книги")
    @Test
    void shouldReturnBookByIdBook() {
        List<Book> listBooks = serviceBook.findAll();
        Book book = listBooks.get(0);
        Optional<Book> bookFind = serviceBook.findById(book.getId());
        assertThat(bookFind.get().getId()).isEqualTo(book.getId());
    }

    @DisplayName("обновлять статус книги")
    @Test
    void shouldUpdateStatusBook() {
        List<Book> listBooks = serviceBook.findAll();
        Book bookBefore = listBooks.get(0);
        String nameBook = bookBefore.getName();
        int statusBookBefore = bookBefore.getStatus();
        serviceBook.update(nameBook, UPDATE_STATUS_BOOK);
        Book bookAfter = serviceBook.findByName(nameBook);
        int statusBookAfter = bookAfter.getStatus();
        assertThat(statusBookBefore).isNotEqualTo(statusBookAfter);
    }

    @DisplayName("возвращать корректное число комментариев из книги")
    @Test
    void shouldReturnCorrectCountCommentsBook() {
        List<Book> listBooks = serviceBook.findAll();
        Book bookBefore = listBooks.get(0);
        List<Comment> listComments = bookBefore.getComments();
        int countComments = serviceBook.getCountCommentsAboutBook(bookBefore.getName());
        assertThat(listComments).isNotNull();
        assertThat(listComments).hasSize(countComments);
    }

    @DisplayName("удалять книгу")
    @Test
    void shouldCorrectDeleteBook() {
        int countBooksBefore = serviceBook.findAll().size();
        List<Book> listBooks = serviceBook.findAll();
        Book book = listBooks.get(0);
        serviceBook.deleteByBook(book);
        int countBooksAfter = serviceBook.findAll().size();
        assertThat(countBooksAfter).isEqualTo(countBooksBefore - 1);
    }

    @DisplayName("удалять книгу по имени книги")
    @Test
    void shouldCorrectDeleteBookByNameBook() {
        int countBooksBefore = serviceBook.findAll().size();
        List<Book> listBooks = serviceBook.findAll();
        Book book = listBooks.get(0);
        serviceBook.deleteByNameBook(book.getName());
        int countBooksAfter = serviceBook.findAll().size();
        assertThat(countBooksAfter).isEqualTo(countBooksBefore - 1);
    }

    @DisplayName("НЕ добавлять книгу с одинаковым названием")
    @Test
    void shouldAddBookWithoutDuplicate() {
        List<Book> listBooks = serviceBook.findAll();
        int countBooksBefore = listBooks.size();
        Book book = listBooks.get(0);
        serviceBook.insert(book);
        int countBooksAfter = listBooks.size();
        assertThat(countBooksAfter).isEqualTo(countBooksBefore);
    }

    @DisplayName("НЕ добавлять книгу(по имени книги) с одинаковым названием")
    @Test
    void shouldAddBookByNameBookWithoutDuplicate() {
        List<Book> listBooks = serviceBook.findAll();
        int countBooksBefore = listBooks.size();
        Book book = listBooks.get(0);
        serviceBook.add(book.getName(), "newTestAuthor", "newTestGenre");
        int countBooksAfter = listBooks.size();
        assertThat(countBooksAfter).isEqualTo(countBooksBefore);
    }
}
