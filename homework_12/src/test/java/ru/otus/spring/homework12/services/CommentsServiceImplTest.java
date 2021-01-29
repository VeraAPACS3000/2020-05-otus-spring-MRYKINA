package ru.otus.spring.homework12.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import ru.otus.spring.homework12.models.Comment;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@DisplayName("Класс сервиса для работы с комментариями должен")
@SpringBootTest
public class CommentsServiceImplTest {

    private static final String UPDATE_NEW_COMMENT = "update new best comment test";
    private static final long FIND_BY_ID = 1l;
    private static final String FIND_BY_NAME = "David Copperfield";

    @Autowired
    EntityManager emTest;

    @Autowired
    CommentsService commentsService;

    @DisplayName("обновить 1 комментарий")
    @Test
    public void shouldUpdateComment() {
        Comment commentBeforeEntityManager = emTest.find(Comment.class, 1l);
        String commentBeforeEntity = commentBeforeEntityManager.getTextComment();
        System.out.println("comment before:" + commentBeforeEntity);
        emTest.detach(commentBeforeEntityManager);
        commentsService.updateCommentById(1, UPDATE_NEW_COMMENT);
        Comment commentAfterEntityManager = emTest.find(Comment.class, 1l);
        String commentAfterEntity = commentAfterEntityManager.getTextComment();
        assertEquals(commentAfterEntity, UPDATE_NEW_COMMENT);
    }

   @DisplayName("загружать список комментариев по id книги. Не null")
    @Test
    public void shouldFindAllCommentsByIdBookNotNull() {
        List<Comment> commentList = commentsService.findCommentByIdBook(FIND_BY_ID);
        assertThat(commentList).isNotNull();
    }

    @DisplayName("загружать список комментариев по названию книги")
    @Test
    public void shouldFindAllCommentsByNameBook() {
        List<Comment> commentList = commentsService.findCommentsByNameBook(FIND_BY_NAME);
        assertThat(commentList).isNotNull();
    }

}
