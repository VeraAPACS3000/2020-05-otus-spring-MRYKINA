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
                .allMatch(s -> !s.getTextComment().equals(""));
        commentList.forEach(System.out::println);
    }

    //-------------------insert 1 comment---------------------------------------------------
    @DisplayName("добавиться новый комментарий не NULL")
    @Test
    public void shouldInsertNewCommentNotNull() {
        Comment comment = new Comment(1, INSERT_NEW_COMMENT);
        commentsRepositoriesJpa.insert(comment);
        assertThat(entityManager.find(Comment.class, 1l)).isNotNull();
    }

    //-----------------update 1 comment----------------------------------------------------
    @DisplayName("обновиться комментарий")
    @Test
    public void shouldUpdateComment() {
        Comment commentBeforeEntityManager = entityManager.find(Comment.class, 1l);
        String commentBeforeEntity = commentBeforeEntityManager.getTextComment();
        System.out.println("comment before:" + commentBeforeEntity);
        entityManager.detach(commentBeforeEntityManager);
        commentsRepositoriesJpa.updateComment(1, UPDATE_NEW_COMMENT);
        Comment commentAfterEntityManager = entityManager.find(Comment.class, 1l);
        String commentAfterEntity = commentAfterEntityManager.getTextComment();
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
