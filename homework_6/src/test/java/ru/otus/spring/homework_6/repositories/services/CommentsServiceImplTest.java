package ru.otus.spring.homework_6.repositories.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import ru.otus.spring.homework_6.models.Book;
import ru.otus.spring.homework_6.models.Comment;
import ru.otus.spring.homework_6.services.CommentsService;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@DisplayName("Тестирование сервиса для работы с комментариями")
@SpringBootTest
public class CommentsServiceImplTest {

    private static final long FIND_BY_ID = 1l;
    private static final long UPDATE_BY_ID = 1l;
    private static final long DELETE_ONE_BY_ID = 4l;
    private static final long DELETE_BY_ID = 5l;
    private static final String FIND_BY_NAME = "David Copperfield";
    private static final long DELETE_SIZE = 1;
    private static final String UPDATE_NEW_COMMENT = "update new comment";

    @Autowired
    CommentsService commentsService;

    @Autowired
    EntityManager entityManager;

    @DisplayName("Выборка всех комментариев")
    @Test
    void shouldReturnListComments() {
        final List<Comment> commentList = commentsService.getAllComments();
        assertThat(commentList).isNotNull();
    }

    @DisplayName("Поиск комментариев по id книги")
    @Test
    public void shouldFindAllCommentsByIdBook() {
        List<Comment> commentList = commentsService.findCommentByIdBook(FIND_BY_ID);
        assertThat(commentList).isNotNull();
    }

    @DisplayName("Поиск комментариев по названию книги")
    @Test
    public void shouldFindAllCommentsByNameBook() {
        List<Comment> commentList = commentsService.findCommentsByNameBook(FIND_BY_NAME);
        assertThat(commentList).isNotNull();
    }

    @DisplayName("удалится 1 комментарий")
    @Test
    public void shouldDeleteOnlyOneComment() {
        List<Comment> commentList = commentsService.getAllComments();
        int sizeBefore = commentList.size();
        commentsService.deleteCommentByIdBook(DELETE_ONE_BY_ID);
        List<Comment> commentListAfter = commentsService.getAllComments();
        int sizeAfter = commentListAfter.size();
        assertEquals(sizeBefore - sizeAfter, DELETE_SIZE);
    }

    @DisplayName("удалится комментарий")
    @Test
    public void shouldDeleteComment() {
        Comment comment = entityManager.find(Comment.class, DELETE_BY_ID);
        assertNotNull(comment);
        commentsService.deleteCommentByIdBook(DELETE_BY_ID);
        assertNull(entityManager.find(Comment.class, DELETE_BY_ID));
    }

    @DisplayName("Обновление комментария")
    @Test
    public void shouldUpdateComment() {
        Comment commentBefore = entityManager.find(Comment.class, UPDATE_BY_ID);
        String beforeComment = commentBefore.getTextComment();
        entityManager.detach(commentBefore);
        commentsService.updateCommentById(UPDATE_BY_ID, UPDATE_NEW_COMMENT);
        Comment commentAfter = entityManager.find(Comment.class, UPDATE_BY_ID);
        String afterComment = commentAfter.getTextComment();
        assertNotEquals(beforeComment, afterComment);
    }

}
