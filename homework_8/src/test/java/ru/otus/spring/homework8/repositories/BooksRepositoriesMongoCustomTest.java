package ru.otus.spring.homework8.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import ru.otus.spring.homework8.model.Book;
import ru.otus.spring.homework8.model.Comment;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
@DisplayName("BooksRepositoriesMongo должен ")
public class BooksRepositoriesMongoCustomTest {

    private static int UPDATE_STATUS_BOOK = 7;
    private static String DELETE_NAME_BOOK = "Vojna i mir TEST";
    private static String FIND_BY_NAME_BOOK = "Besi TEST";

    @Autowired
    private BooksRepositoriesMongo repoBook;

    @Autowired
    private CommentsRepositpriesMongo repoComment;

    @DisplayName("обновлять статус книги")
    @Test
    void shouldUpdateStatusBook() {
        List<Book> listBooks = repoBook.findAll();
        Book bookBefore = listBooks.get(0);
        String nameBook = bookBefore.getName();
        int statusBookBefore = bookBefore.getStatus();
        repoBook.updateStatus(nameBook, UPDATE_STATUS_BOOK);
        Book bookAfter = repoBook.findByName(nameBook);
        int statusBookAfter = bookAfter.getStatus();
        System.out.println("status before:" + statusBookBefore);
        System.out.println("status after:" + statusBookAfter);
        assertThat(statusBookBefore).isNotEqualTo(statusBookAfter);
    }

    @DisplayName("вернуть список книг")
    @Test
    void shouldReturnListBooks() {
        List<Book> listBook = repoBook.findAll();
        assertThat(listBook).isNotNull();
        System.out.println("list books:" + listBook);
    }

    @DisplayName("найти книгу по названию")
    @Test
    void shouldFindBookByName() {
        Book book = repoBook.findByName(FIND_BY_NAME_BOOK);
        assertThat(book).isNotNull();
        System.out.println(book.getName());
    }

    @DisplayName("возвращать корректное число комментариев из книги")
    @Test
    void shouldReturnCorrectCountCommentsBook() {
        List<Book> listBooks = repoBook.findAll();
        Book bookBefore = listBooks.get(0);
        List<Comment> listComments = bookBefore.getComments();
        int countComments = repoBook.getCommentsArrayLengthByBook(bookBefore.getName());
        assertThat(listComments).isNotNull();
        assertThat(listComments).hasSize(countComments);
    }

    @DisplayName("удалять книгу")
    @Test
    void shouldCorrectDeleteBook() {
        int countBooksBefore = repoBook.findAll().size();
        repoBook.deleteBookByName(DELETE_NAME_BOOK);
        int countBooksAfter = repoBook.findAll().size();
        System.out.println("count before:" + countBooksBefore);
        System.out.println("count after:" + countBooksAfter);
        assertThat(countBooksAfter).isEqualTo(countBooksBefore - 1);
    }

    @DisplayName("НЕ добавлять книгу с одинаковым названием")
    @Test
    void shouldAddBookWithoutDuplicate() {
        List<Book> listBooks = repoBook.findAll();
        int countBooksBefore = listBooks.size();
        Book book = listBooks.get(0);
        repoBook.insertBookWithoutDuplicateName(book);
        int countBooksAfter = listBooks.size();
        System.out.println("count before:" + countBooksBefore);
        System.out.println("count after:" + countBooksAfter);
        assertThat(countBooksAfter).isEqualTo(countBooksBefore);
    }
}
