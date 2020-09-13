package ru.otus.spring.homework9.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;
import ru.otus.spring.homework9.models.Author;
import ru.otus.spring.homework9.models.Book;
import ru.otus.spring.homework9.models.Genre;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@DisplayName("Репозиторий для работы с книгами должен")
@DataJpaTest
public class BooksRepositoriesJpaTest {

    private static final long FIND_BY_ID = 1l;
    private static final int BOOK_LIST_SIZE = 5;
    private static final String INSERT_NAME_AUTHOR = "Steven King";
    private static final String INSERT_NAME_GENRE = "lyrics";
    private static final String INSERT_NAME_BOOK = "test new book";
    private static final long DELETE_SIZE = 1;
    private static final long UPDATE_BY_ID = 1l;
    private static final int UPDATE_STATUS = 8;

    @Autowired
    TestEntityManager emTest;

    @Autowired
    BooksRepositoriesJpa repoBooks;

    //-------------------select 1 book---------------------------------------------------
    @DisplayName("загружать информацию о нужной книге по его id")
    @Test
    void shouldFindExpectedBookById() {
        Optional<Book> optionalActualBook = repoBooks.findById(FIND_BY_ID);
        Book expectedBook = emTest.find(Book.class, FIND_BY_ID);
        assertThat(optionalActualBook).isPresent().get()
                .isEqualToComparingFieldByField(expectedBook);
    }

    @DisplayName("загружать информацию о нужной книге по её названию")
    @ParameterizedTest
    @MethodSource("generateDataTestBookByName")
    void shouldFindExpectedBookByName(String nameBook) {
        Optional<Book> optionalBook = repoBooks.findByName(nameBook);
        long idBook = optionalBook.get().getId();
        Book expectedBook = emTest.find(Book.class, idBook);
        assertThat(optionalBook).isPresent().get()
                .isEqualToComparingFieldByField(expectedBook);

    }

    private static Stream<Arguments> generateDataTestBookByName() {
        return Stream.of(
                Arguments.of("Misery"),
                Arguments.of("Desperation"),
                Arguments.of("To Chaadaev"),
                Arguments.of("I remember a wonderful moment"),
                Arguments.of("David Copperfield")
        );
    }

    @DisplayName("загружать 1 книгу не null")
    @Test
    public void shouldReturnBookByIdNotNull() {
        final Optional<Book> optionalActualBook = repoBooks.findById(FIND_BY_ID);
        assertThat(optionalActualBook).isPresent();
    }

    //-------------------select ALL books------------------------------------------------
    @DisplayName("загружать список книг не null")
    @Test
    void shouldReturnListBookNotNull() {
        final List<Book> bookList = (List<Book>) repoBooks.findAll();
        assertThat(bookList).isNotNull();
    }

    @DisplayName("загружать корректное число книг")
    @Test
    void shouldReturnCorrectNumberRows() {
        List<Book> bookList = (List<Book>) repoBooks.findAll();
        assertThat(bookList).hasSize(BOOK_LIST_SIZE);
    }

    @DisplayName("загружать список всех книг с полной информацией о них")
    @Test
    void shouldReturnListBooksCorrectValuesNotNull() {
        final List<Book> bookList = (List<Book>) repoBooks.findAll();
        assertThat(bookList)
                .allMatch(s -> !s.getName().equals(""))
                .allMatch(s -> s.getAuthor() != null)
                .allMatch(s -> s.getGenre() != null);
        bookList.forEach(System.out::println);
    }

    //-------------------insert 1 book---------------------------------------------------
    @DisplayName("добавить новую книгу не NULL")
    @Test
    public void shouldInsertNewBookNotNull() {
        Book book = new Book(INSERT_NAME_BOOK, new Author(0, INSERT_NAME_AUTHOR), new Genre(0, INSERT_NAME_GENRE));
        repoBooks.save(book);
        Optional<Book> actualBook = repoBooks.findByName(INSERT_NAME_BOOK);
        assertThat(actualBook).isPresent();
    }

    @DisplayName("добавить новую книгу с ожидаемым названием")
    @Test
    public void shouldInsertNewBookExpectedName() {
        Book book = new Book(INSERT_NAME_BOOK, new Author(0, INSERT_NAME_AUTHOR), new Genre(0, INSERT_NAME_GENRE));
        repoBooks.save(book);
        Optional<Book> actualBook = repoBooks.findByName(INSERT_NAME_BOOK);
        String actualBookName = actualBook.isPresent() ? actualBook.get().getName() : "none";
        assertEquals(actualBookName, INSERT_NAME_BOOK);
    }

    //-----------------delete 1 book----------------------------------------------------
    @DisplayName("удалить ровно 1 книгу")
    @Test
    public void shouldDeleteBook() {
        List<Book> bookListBefore = (List<Book>) repoBooks.findAll();
        int sizeBefore = bookListBefore.size();
        Book book = emTest.find(Book.class, 5l);
        repoBooks.delete(book);
        List<Book> bookListAfter = (List<Book>) repoBooks.findAll();
        int sizeAfter = bookListAfter.size();
        assertEquals(sizeBefore - sizeAfter, DELETE_SIZE);
    }

    //-----------------update 1 book----------------------------------------------------
    @DisplayName("обновить статус книги")
    @Test
    public void shouldUpdateStatusDifferentBeforeAfter() {
        Book bookBefore = emTest.find(Book.class, UPDATE_BY_ID);
        int beforeStatus = bookBefore.getStatus();
        emTest.detach(bookBefore);
        repoBooks.updateBookStatus(UPDATE_BY_ID, UPDATE_STATUS);
        Book bookAfter = emTest.find(Book.class, UPDATE_BY_ID);
        int afterStatus = bookAfter.getStatus();
        assertNotEquals(beforeStatus, afterStatus);
    }
}
