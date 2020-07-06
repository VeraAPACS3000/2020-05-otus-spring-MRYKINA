package ru.otus.spring.homework_5.shell;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.Shell;
import org.springframework.test.annotation.DirtiesContext;
import ru.otus.spring.homework_5.dao.BookDaoJdbc;
import ru.otus.spring.homework_5.service.BooksService;

import static org.assertj.core.api.Assertions.assertThat;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@DisplayName("Тестирование команд shell")
@SpringBootTest
public class ApplicationEventsCommandsTest {

    @Autowired
    private Shell shell;

    @Autowired
    BooksService booksService;

    @Autowired
    BookDaoJdbc bookDaoJdbc;

    private static final String SELECT_COMMAND = "s";
    private static final String RESULT_SELECT_COMMAND = "Показали весь список книг из библиотеки";

    private static final String INSERT_COMMAND = "i";
    private static final String RESULT_INSERT_COMMAND = "Добавлена новая книга";

    private static final String UPDATE_COMMAND = "u";
    private static final String RESULT_UPDATE_COMMAND = "Изменили статус книги";

    private static final String DELETE_COMMAND = "d";
    private static final String RESULT_DELETE_COMMAND = "Удалили книгу";

    @DisplayName("Должен возвращать ответ: Показали весь список книг из библиотеки")
    @Test
    void shouldReturnAllListBooks() {
        String resultCommand = (String) shell.evaluate(() -> SELECT_COMMAND);
        assertThat(resultCommand).isEqualTo(RESULT_SELECT_COMMAND);
        //verify(booksService, times(1)).getAllBooks();
    }

    @DisplayName("Должен возвращать ответ: Добавлена новая книга")
    @Test
    void shouldInsertBook() {
        String resultCommand = (String) shell.evaluate(() -> INSERT_COMMAND + " newBook 1 1");
        assertThat(resultCommand).isEqualTo(RESULT_INSERT_COMMAND);
    }

    @DisplayName("Должен возвращать ответ: Изменили статус книги")
    @Test
    void shouldUpdateBook() {
        String resultCommand = (String) shell.evaluate(() -> UPDATE_COMMAND + " 1 0");
        assertThat(resultCommand).isEqualTo(RESULT_UPDATE_COMMAND);
        // verify(booksService, times(1)).updateStatusBook(1, 0);
    }

   /* @DisplayName("Должен возвращать ответ: Удалили книгу")
    @Test
    void shouldDeleteBook() {
        String resultCommand = (String) shell.evaluate(() -> DELETE_COMMAND + " 1");
        assertThat(resultCommand).isEqualTo(RESULT_DELETE_COMMAND);
        verify(booksService, times(1)).deleteBookById(1);
    }*/

    //@Bean
    // public DataSource h2DataSource() {
    // return new EmbeddedDatabaseBuilder()
    //  .setType(EmbeddedDatabaseType.H2)
    //.setName("testDB;TRACE_LEVEL_SYSTEM_OUT=3")
    //  .addScript("data.sql").addScript("schema.sql").build();
        /*JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setURL("jdbc:h2:mem:TRACE_LEVEL_SYSTEM_OUT=3;");
        dataSource.setUser("sa");
        dataSource.setPassword("sa");*/

    // return dataSource;

    //}
}
