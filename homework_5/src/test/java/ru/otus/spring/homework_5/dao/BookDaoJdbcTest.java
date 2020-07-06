package ru.otus.spring.homework_5.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import ru.otus.spring.homework_5.domain.Authors;
import ru.otus.spring.homework_5.domain.Books;
import ru.otus.spring.homework_5.domain.Genres;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@DisplayName("Тестирование слоя DAO. Операции вставки, удаления, выборки, обновление")
@SpringBootTest
public class BookDaoJdbcTest {

    private static final long BOOK_ID = 6l;
    private static final long FIND_BY_ID = 1l;
    private static final long UPDATE_BY_ID = 1l;
    private static final long DELETE_BY_ID = 4l;
    private static final int BOOK_LIST_SIZE = 5;
    private static final String FIELD_NAME = "name";

    @Autowired
    BookDaoJdbc bookDaoJdbc;

    @DisplayName("Выборка всех книг. Список не null")
    @Test
    void testReturnListNotNull() {
        final List<Books> booksList = bookDaoJdbc.getAll();
        assertThat(booksList).isNotNull();
    }

    @DisplayName("Выборка всех книг. Должен возвращать корректное число записей")
    @Test
    void testCorrectNumberReturnRows() {
        final List<Books> booksList = bookDaoJdbc.getAll();
        assertThat(booksList).hasSize(BOOK_LIST_SIZE);
    }

    @DisplayName("Выборка всех книг. Поля таблицы не должны быть null/пустыми")
    @Test
    void testReturnLisCorrectValuesNotNull() {
        final List<Books> booksList = bookDaoJdbc.getAll();
        assertThat(booksList)
                .allMatch(s -> !s.getName().equals(""))
                .allMatch(s -> s.getAuthors() != null)
                .allMatch(s -> s.getGenres() != null);
        booksList.forEach(System.out::println);
    }

    @DisplayName("Вставка 1 книги. Должна добавиться запись. И для проверки найти её")
    @Test
    public void testInsertBook() {
        final Authors authors = new Authors(1, "Steven King");
        final Genres genres = new Genres(2, "lyrics");
        final Books books = new Books("test new book", authors, genres);
        bookDaoJdbc.insert(books);
        final Books actualBook = bookDaoJdbc.findById(BOOK_ID);
        assertThat(actualBook).isNotNull().isEqualToComparingOnlyGivenFields(books, FIELD_NAME);
    }

    @DisplayName("Поиск 1 книги по её id. Должна вернутся запись не null")
    @Test
    public void testFindByIdNotNull() {
        final Books actualBook = bookDaoJdbc.findById(FIND_BY_ID);
        assertThat(actualBook).isNotNull();
    }

    @DisplayName("Поиск одной книги по её id. Должны вернутся все книги по-очереди с ожидаемыми полями")
    @ParameterizedTest
    @MethodSource("generateDataValuesReturnBook")
    public void testFindById(long idBook, String nameBook, String nameAuthor, long idAuthor,
                             String nameGenre, long idGenre) {
        final Books books = bookDaoJdbc.findById(idBook);
        assertEquals(books.getId(), idBook);
        assertEquals(books.getName(), nameBook);
        assertEquals(books.getAuthors().getName(), nameAuthor);
        assertEquals(books.getAuthors().getId(), idAuthor);
        assertEquals(books.getGenres().getName(), nameGenre);
        assertEquals(books.getGenres().getId(), idGenre);
    }

    private static Stream<Arguments> generateDataValuesReturnBook() {
        return Stream.of(
                Arguments.of(2, "I remember a wonderful moment", "Alexandr Pushkin", 2, "lyrics", 2),
                Arguments.of(3, "Misery", "Steven King", 1, "thriller", 1),
                Arguments.of(4, "Desperation", "Steven King", 1, "novel", 3),
                Arguments.of(5, "David Copperfield", "Charles Dickens", 3, "novel", 3)
        );
    }

    @DisplayName("Обновление статуса книги. Статус ПОСЛЕ и должен быть отличен от ДО и равен ожидаемому значению")
    @Test
    public void testUpdateByStatus() {
        Books booksBefore = bookDaoJdbc.findById(UPDATE_BY_ID);
        bookDaoJdbc.updateByStatus(UPDATE_BY_ID, 0);
        Books booksAfter = bookDaoJdbc.findById(UPDATE_BY_ID);
        assertNotEquals(booksBefore.getStatus(), booksAfter.getStatus());
        assertEquals(booksAfter.getStatus(), 0);
    }

    @DisplayName("Должна удаляться запись. Размеры списков книг ДО и ПОСЛЕ отличны на 1")
    @Test
    public void testDeleteById() {
        List<Books> booksListBefore = bookDaoJdbc.getAll();
        int sizeBefore = booksListBefore.size();
        bookDaoJdbc.deleteById(DELETE_BY_ID);
        List<Books> booksListAfter = bookDaoJdbc.getAll();
        int sizeAfter = booksListAfter.size();
        assertEquals(sizeBefore - sizeAfter, 1);
    }
}
