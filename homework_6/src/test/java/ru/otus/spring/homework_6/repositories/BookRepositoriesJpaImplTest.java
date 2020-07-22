package ru.otus.spring.homework_6.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import ru.otus.spring.homework_6.models.Author;
import ru.otus.spring.homework_6.models.Book;
import ru.otus.spring.homework_6.models.Genre;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@DisplayName("Репозиторий для работы с книгами должен")
@DataJpaTest
@Import({BooksRepositoriesJpaImpl.class, AuthorRepositoriesJpaImpl.class})
public class BookRepositoriesJpaImplTest {

    private static final long FIND_BY_ID = 1l;
    private static final String FIND_BY_NAME = "I remember a wonderful moment";
    private static final int BOOK_LIST_SIZE = 5;
    private static final String INSERT_NAME_AUTHOR = "Steven King";
    private static final String INSERT_NAME_GENRE = "lyrics";
    private static final String INSERT_NAME_BOOK = "test new book";
    private static final long DELETE_SIZE = 1;

    @Autowired
    BooksRepositoriesJpaImpl repositoriesJpa;

    @Autowired
    AuthorRepositoriesJpaImpl authorRepositoriesJpa;

    @Autowired
    TestEntityManager entityManager;

    //-------------------select 1 book---------------------------------------------------
    @DisplayName("загружать информацию о нужной книге по его id")
    @Test
    void shouldFindExpectedBookById() {
        Optional<Book> optionalActualBook = repositoriesJpa.findById(FIND_BY_ID);
        Book expectedBook = entityManager.find(Book.class, FIND_BY_ID);
        assertThat(optionalActualBook).isPresent().get()
                .isEqualToComparingFieldByField(expectedBook);
    }

    @DisplayName("загружать информацию о нужной книге по названию")
    @Test
    void shouldFindExpectedBookByName() {
        Book book = repositoriesJpa.findByName(FIND_BY_NAME);
        long id = book.getId();
        Book expectedBook = entityManager.find(Book.class, id);
        assertThat(book).isEqualToComparingFieldByField(expectedBook);
    }

    @DisplayName("загрузиться все книги по-очереди с ожидаемыми полями")
    @ParameterizedTest
    @MethodSource("generateDataValuesReturnBook")
    public void shouldReturnBookByIdBookCorrectFields(long idBook, String nameBook, String nameAuthor, long idAuthor,
                                                      String nameGenre, long idGenre) {
        final Optional<Book> optionalBooks = repositoriesJpa.findById(idBook);
        Book book = optionalBooks.get();
        assertEquals(book.getId(), idBook);
        assertEquals(book.getName(), nameBook);
        assertEquals(book.getAuthor().getName(), nameAuthor);
        assertEquals(book.getAuthor().getId(), idAuthor);
        assertEquals(book.getGenre().getName(), nameGenre);
        assertEquals(book.getGenre().getId(), idGenre);
    }

    private static Stream<Arguments> generateDataValuesReturnBook() {
        return Stream.of(
                Arguments.of(1, "Misery", "Steven King", 1, "thriller", 1),
                Arguments.of(2, "Desperation", "Steven King", 1, "novel", 3),
                Arguments.of(3, "To Chaadaev", "Alexandr Pushkin", 2, "letter", 4),
                Arguments.of(4, "I remember a wonderful moment", "Alexandr Pushkin", 2, "lyrics", 2),
                Arguments.of(5, "David Copperfield", "Charles Dickens", 3, "novel", 3)
        );
    }

    @DisplayName("вернутся 1 книга не null")
    @Test
    public void shouldReturnBookByIdNotNull() {
        final Optional<Book> optionalActualBook = repositoriesJpa.findById(FIND_BY_ID);
        assertThat(optionalActualBook).isPresent().get().isNotNull();
    }

    //-------------------select ALL books------------------------------------------------
    @DisplayName("список книг не null")
    @Test
    void shouldReturnListBookNotNull() {
        final List<Book> bookList = repositoriesJpa.getAll();
        assertThat(bookList).isNotNull();
    }

    @DisplayName("возвращать корректное число книг")
    @Test
    void shouldReturnCorrectNumberRows() {
        List<Book> bookList = repositoriesJpa.getAll();
        assertThat(bookList).hasSize(BOOK_LIST_SIZE);
    }

    @DisplayName("вернуть поля таблицы Книги не null/пустыми")
    @Test
    void shouldReturnListBooksCorrectValuesNotNull() {
        final List<Book> bookList = repositoriesJpa.getAll();
        assertThat(bookList)
                .allMatch(s -> !s.getName().equals(""))
                .allMatch(s -> s.getAuthor() != null)
                .allMatch(s -> s.getGenre() != null);
        bookList.forEach(System.out::println);
    }

    //-------------------insert 1 book---------------------------------------------------
    @DisplayName("добавиться новая книга не NULL")
    @Test
    public void shouldInsertNewBookNotNull() {
        Book book = new Book(INSERT_NAME_BOOK, new Author(0, INSERT_NAME_AUTHOR), new Genre(0, INSERT_NAME_GENRE));
        repositoriesJpa.insert(book);
        Book actualBook = repositoriesJpa.findByName(INSERT_NAME_BOOK);
        assertThat(actualBook).isNotNull();
    }

    @DisplayName("добавиться новая книга с ожидаемым названием")
    @Test
    public void shouldInsertNewBookExpectedName() {
        Book book = new Book(INSERT_NAME_BOOK, new Author(0, INSERT_NAME_AUTHOR), new Genre(0, INSERT_NAME_GENRE));
        repositoriesJpa.insert(book);
        Book actualBook = repositoriesJpa.findByName(INSERT_NAME_BOOK);
        assertEquals(actualBook.getName(), INSERT_NAME_BOOK);
    }

    //-----------------delete 1 book----------------------------------------------------
    @DisplayName("размер списка книг ДО удаления и ПОСЛЕ отличны на 1")
    @Test
    public void shouldDeleteBook() {
        List<Book> bookListBefore = repositoriesJpa.getAll();
        int sizeBefore = bookListBefore.size();
        Book book = entityManager.find(Book.class, 5l);
        repositoriesJpa.delete(book);
        List<Book> bookListAfter = repositoriesJpa.getAll();
        int sizeAfter = bookListAfter.size();
        assertEquals(sizeBefore - sizeAfter, DELETE_SIZE);
    }

}
