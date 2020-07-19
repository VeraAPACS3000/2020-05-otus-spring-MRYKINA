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
import ru.otus.spring.homework_6.models.Comment;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@DisplayName("Репозиторий для работы с комментариями должен")
@DataJpaTest
@Import(CommentsRepositoriesJpaImpl.class)
public class CommentsRepositoriesJpaImplTest {

    private static final long FIND_BY_ID = 1l;
    private static final String FIND_BY_NAME = "Desperation";
    private static final int ALL_COMMENTS_SIZE = 12;
    private static final String INSERT_NEW_COMMENT = "new best comment test";
    private static final String UPDATE_NEW_COMMENT = "update new best comment test";
    private static final long DELETE_BY_ID = 2l;
    private static final long DELETE_SIZE = 1l;

    @Autowired
    CommentsRepositoriesJpaImpl commentsRepositoriesJpa;

    @Autowired
    TestEntityManager entityManager;

    //-------------------select 1 comment---------------------------------------------------

    @DisplayName("загружать список комментариев по id книги не NULL")
    @Test
    void shouldFindAllCommentsByIdBook() {
        final List<Comment> commentList = commentsRepositoriesJpa.findByIdBook(FIND_BY_ID);
        assertNotNull(commentList);
    }

    @DisplayName("загружать список комментариев по id книги с ожидаемым количеством для своей книги")
    @ParameterizedTest
    @MethodSource("dataTestReturnCorrectSizeListComments")
    void shouldReturnCorrectSizeListCommentsByIdBook(long idBook, int sizeShouldListComments) {
        final List<Comment> commentList = commentsRepositoriesJpa.findByIdBook(idBook);
        assertEquals(commentList.size(), sizeShouldListComments);
    }

    private static Stream<Arguments> dataTestReturnCorrectSizeListComments() {
        return Stream.of(
                Arguments.of(1, 2),//для книги id=1 должно быть 4 комментария
                Arguments.of(2, 1),
                Arguments.of(3, 3),
                Arguments.of(4, 2),
                Arguments.of(5, 4)
        );
    }

    @DisplayName("загружать список комментариев по Названию книги не NULL")
    @Test
    void shouldFindAllCommentsByNameBook() {
        final List<Comment> commentList = commentsRepositoriesJpa.findByNameBook(FIND_BY_NAME);
        assertNotNull(commentList);
    }

    @DisplayName("загружать список комментариев по Названию книги с ожидаемым количеством для своей книги")
    @ParameterizedTest
    @MethodSource("dataTestReturnCorrectSizeListCommentsByNameBook")
    void shouldReturnCorrectSizeListCommentsByNameBook(String nameBook, int sizeShouldListComments) {
        final List<Comment> commentList = commentsRepositoriesJpa.findByNameBook(nameBook);
        assertEquals(commentList.size(), sizeShouldListComments);
    }

    private static Stream<Arguments> dataTestReturnCorrectSizeListCommentsByNameBook() {
        return Stream.of(
                Arguments.of("Misery", 2),
                Arguments.of("Desperation", 1),
                Arguments.of("To Chaadaev", 3),
                Arguments.of("I remember a wonderful moment", 2),
                Arguments.of("David Copperfield", 4)
        );
    }

    //-------------------select ALL comments---------------------------------------------------

    @DisplayName("загружать весь список комментариев не NULL")
    @Test
    void shouldReturnListAllCommentsNotNull() {
        final List<Comment> commentList = commentsRepositoriesJpa.getAll();
        assertNotNull(commentList);
    }

    @DisplayName("загружать весь список комментариев с ожидаемым количеством")
    @Test
    void shouldReturnCorrectSizeListAllComments() {
        final List<Comment> commentList = commentsRepositoriesJpa.getAll();
        assertEquals(commentList.size(), ALL_COMMENTS_SIZE);
    }

    @DisplayName("вернуть поля таблицы Комментарии не null/пустыми")
    @Test
    void shouldReturnListCommentsCorrectValuesNotNull() {
        final List<Comment> commentList = commentsRepositoriesJpa.getAll();
        assertThat(commentList)
                .allMatch(s -> !s.getText_comment().equals(""));
        commentList.forEach(System.out::println);
    }

    //-------------------insert 1 comment---------------------------------------------------
    @DisplayName("добавиться новый комментарий не NULL")
    @Test
    public void shouldInsertNewCommentNotNull() {
        Comment comment = new Comment(1, INSERT_NEW_COMMENT);
        commentsRepositoriesJpa.insert(comment);
        assertThat(commentsRepositoriesJpa.findByIdBook(1)).isNotNull();
    }

    //-----------------update 1 comment----------------------------------------------------
    @DisplayName("обновиться комментарий")
    @Test
    public void shouldUpdateComment() {
        Comment commentBeforeEntityManager = entityManager.find(Comment.class, 1l);
        String commentBeforeEntity = commentBeforeEntityManager.getText_comment();
        System.out.println("comment before:" + commentBeforeEntity);
        entityManager.detach(commentBeforeEntityManager);
        commentsRepositoriesJpa.updateComment(1, UPDATE_NEW_COMMENT);
        Comment commentAfterEntityManager = entityManager.find(Comment.class, 1l);
        String commentAfterEntity = commentAfterEntityManager.getText_comment();
        assertEquals(commentAfterEntity, UPDATE_NEW_COMMENT);
    }

    //-----------------delete 1 comment----------------------------------------------------
    @DisplayName("удалить комментарий")
    @Test
    public void shouldDeleteComment() {
        List<Comment> bookListComment = commentsRepositoriesJpa.getAll();
        int sizeBefore = bookListComment.size();
        commentsRepositoriesJpa.deleteById(DELETE_BY_ID);
        List<Comment> bookListAfter = commentsRepositoriesJpa.getAll();
        int sizeAfter = bookListAfter.size();
        assertEquals(sizeBefore - sizeAfter, DELETE_SIZE);
    }
}
