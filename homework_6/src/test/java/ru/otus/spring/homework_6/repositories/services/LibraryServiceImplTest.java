package ru.otus.spring.homework_6.repositories.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import ru.otus.spring.homework_6.models.Book;
import ru.otus.spring.homework_6.models.Comment;
import ru.otus.spring.homework_6.services.LibraryService;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@DisplayName("Тестирование сервиса библиотеки книг")
@SpringBootTest
public class LibraryServiceImplTest {

    private static final long FIND_BY_ID = 1l;
    private static final long UPDATE_BY_ID = 1l;
    private static final long DELETE_BY_ID = 4l;
    private static final int BOOK_LIST_SIZE = 5;
    private static final String FIND_BY_NAME = "David Copperfield";
    private static final String INSERT_NAME_AUTHOR = "Steven King";
    private static final String INSERT_NAME_GENRE = "lyrics";
    private static final String INSERT_NAME_BOOK = "test new book";
    private static final int UPDATE_STATUS = 8;
    private static final long DELETE_SIZE = 1;
    private static final String UPDATE_NEW_COMMENT = "update new comment";

    @Autowired
    LibraryService libraryService;

    @Autowired
    EntityManager entityManager;

    //Books
    @DisplayName("Должен вернуть все книги")
    @Test
    void shouldReturnListBooks() {
        final List<Book> booksList = libraryService.getAllBooks();
        assertThat(booksList).isNotNull();
    }

    @DisplayName("Выборка всех книг. Должен возвращать корректное число записей")
    @Test
    void shouldCorrectNumberReturnRows() {
        final List<Book> booksList = libraryService.getAllBooks();
        assertThat(booksList).hasSize(BOOK_LIST_SIZE);
    }

    @DisplayName("Выборка всех книг. Поля таблицы не должны быть null/пустыми")
    @Test
    void shouldReturnLisCorrectValuesNotNull() {
        final List<Book> booksList = libraryService.getAllBooks();
        assertThat(booksList)
                .allMatch(s -> !s.getName().equals(""))
                .allMatch(s -> s.getAuthor() != null)
                .allMatch(s -> s.getGenre() != null);
        booksList.forEach(System.out::println);
    }

    @DisplayName("Поиск 1 книги по её id. Должна вернутся запись не null")
    @Test
    public void shouldFindByIdNotNull() {
        final Optional<Book> actualBook = libraryService.findBookById(FIND_BY_ID);
        assertThat(actualBook).isPresent().get().isNotNull();
    }

    @DisplayName("Поиск одной книги по её id. Должны вернутся все книги по-очереди с ожидаемыми полями")
    @ParameterizedTest
    @MethodSource("generateDataValuesReturnBook")
    public void shouldFindById(long idBook, String nameBook, String nameAuthor, long idAuthor,
                               String nameGenre, long idGenre) {
        final Optional<Book> optionalBook = libraryService.findBookById(idBook);
        Book book = optionalBook.get();
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

    @DisplayName("Поиск одной книги по её названию")
    @Test
    void shouldFindExpectedBookByName() {
        Book book = libraryService.findBookByName(FIND_BY_NAME);
        assertThat(book).isNotNull();
    }

    @DisplayName("добавиться новая книга не NULL")
    @Test
    public void shouldInsertNewBookNotNull() {
        libraryService.addNewBook(INSERT_NAME_BOOK, INSERT_NAME_AUTHOR, INSERT_NAME_GENRE);
        Book actualBook = libraryService.findBookByName(INSERT_NAME_BOOK);
        assertThat(actualBook).isNotNull();
    }

    @DisplayName("обновится статус книги")
    @Test
    public void shouldUpdateStatusDifferentBeforeAfter() {
        Book bookBefore = entityManager.find(Book.class, UPDATE_BY_ID);
        int beforeStatus = bookBefore.getStatus();
        entityManager.detach(bookBefore);
        libraryService.updateStatusBook(UPDATE_BY_ID, UPDATE_STATUS);
        Book bookAfter = entityManager.find(Book.class, UPDATE_BY_ID);
        int afterStatus = bookAfter.getStatus();
        assertNotEquals(beforeStatus, afterStatus);
    }

    @DisplayName("удалится книга")
    @Test
    public void shouldDeleteBook() {
        List<Book> bookListBefore = libraryService.getAllBooks();
        int sizeBefore = bookListBefore.size();
        libraryService.deleteBookById(DELETE_BY_ID);
        List<Book> bookListAfter = libraryService.getAllBooks();
        int sizeAfter = bookListAfter.size();
        assertEquals(sizeBefore - sizeAfter, DELETE_SIZE);
    }

    //Comments
    @DisplayName("Выборка всех комментариев")
    @Test
    void shouldReturnListComments() {
        final List<Comment> commentList = libraryService.getAllComments();
        assertThat(commentList).isNotNull();
    }

    @DisplayName("Поиск комментариев по id книги")
    @Test
    public void shouldFindAllCommentsByIdBook() {
        List<Comment> commentList = libraryService.findCommentByIdBook(FIND_BY_ID);
        assertThat(commentList).isNotNull();
    }

    @DisplayName("Поиск комментариев по названию книги")
    @Test
    public void shouldFindAllCommentsByNameBook() {
        List<Comment> commentList = libraryService.findCommentsByNameBook(FIND_BY_NAME);
        assertThat(commentList).isNotNull();
    }

    @DisplayName("Удаление комментария")
    @Test
    public void shouldDeleteComment() {
        List<Comment> commentList = libraryService.getAllComments();
        int sizeBefore = commentList.size();
        libraryService.deleteCommentByIdBook(DELETE_BY_ID);
        List<Comment> commentListAfter = libraryService.getAllComments();
        int sizeAfter = commentListAfter.size();
        assertEquals(sizeBefore - sizeAfter, DELETE_SIZE);
    }

    @DisplayName("Обновление комментария")
    @Test
    public void shouldUpdateComment() {
        Comment commentBefore = entityManager.find(Comment.class, UPDATE_BY_ID);
        String beforeComment = commentBefore.getText_comment();
        entityManager.detach(commentBefore);
        libraryService.updateCommentById(UPDATE_BY_ID, UPDATE_NEW_COMMENT);
        Comment commentAfter = entityManager.find(Comment.class, UPDATE_BY_ID);
        String afterComment = commentAfter.getText_comment();
        assertNotEquals(beforeComment, afterComment);
    }
}
