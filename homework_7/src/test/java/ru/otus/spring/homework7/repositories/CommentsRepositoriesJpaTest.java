package ru.otus.spring.homework7.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;
import ru.otus.spring.homework7.models.Comment;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@DisplayName("Репозиторий для работы с комментариями должен")
@DataJpaTest
public class CommentsRepositoriesJpaTest {

    private static final int ALL_COMMENTS_SIZE = 12;
    private static final String INSERT_NEW_COMMENT = "new best comment test";
    private static final String UPDATE_NEW_COMMENT = "update new best comment test";
    private static final long DELETE_BY_ID = 2l;
    private static final long DELETE_SIZE = 1l;
    private static final long FIND_BY_ID = 1l;
    private static final long FIND_BY_ID_COMMENT = 8l;
    private static final String FIND_BY_NAME = "David Copperfield";

    @Autowired
    TestEntityManager emTest;

    @Autowired
    CommentsRepositoriesJpa repoComments;

    //-------------------select ALL comments---------------------------------------------------

    @DisplayName("загружать весь список комментариев не NULL")
    @Test
    void shouldReturnListAllCommentsNotNull() {
        final List<Comment> commentList = (List<Comment>) repoComments.findAll();
        assertNotNull(commentList);
    }

    @DisplayName("загружать весь список комментариев с ожидаемым количеством")
    @Test
    void shouldReturnCorrectSizeListAllComments() {
        final List<Comment> commentList = (List<Comment>) repoComments.findAll();
        assertEquals(commentList.size(), ALL_COMMENTS_SIZE);
    }

    @DisplayName("загружать список всех комментариев с полной информацией о них")
    @Test
    void shouldReturnListCommentsCorrectValuesNotNull() {
        final List<Comment> commentList = (List<Comment>) repoComments.findAll();
        assertThat(commentList)
                .allMatch(s -> !s.getTextComment().equals(""));
        commentList.forEach(System.out::println);
    }

    //-------------------select 1 comment---------------------------------------------------
    @DisplayName("загружать 1 комментарий по id комментария")
    @Test
    public void shouldFindAllCommentsByIdComment() {
        Comment expectedComment = emTest.find(Comment.class, FIND_BY_ID_COMMENT);
        Optional<Comment> commentList = repoComments.findById(FIND_BY_ID_COMMENT);
        assertThat(commentList).isPresent().get().isEqualToComparingFieldByField(expectedComment);
    }

    @DisplayName("загружать 1 комментарий по id комментария. Не Null")
    @Test
    public void shouldFindAllCommentsByIdCommentNotNull() {
        Optional<Comment> commentList = repoComments.findById(FIND_BY_ID_COMMENT);
        assertThat(commentList).isPresent();
    }

    @DisplayName("загружать список комментариев по id книги. Не null")
    @Test
    public void shouldFindAllCommentsByIdBookNotNull() {
        List<Comment> commentList = repoComments.findByIdBook(FIND_BY_ID);
        assertThat(commentList).isNotNull();
    }

    @DisplayName("загружать список комментариев по названию книги")
    @Test
    public void shouldFindAllCommentsByNameBook() {
        List<Comment> commentList = repoComments.findByNameBook(FIND_BY_NAME);
        assertThat(commentList).isNotNull();
    }

    //-------------------insert 1 comment---------------------------------------------------
    @DisplayName("добавить 1 новый комментарий не NULL")
    @Test
    public void shouldInsertNewCommentNotNull() {
        Comment comment = new Comment(1, INSERT_NEW_COMMENT);
        repoComments.save(comment);
        assertThat(emTest.find(Comment.class, 1l)).isNotNull();
    }

    //-----------------update 1 comment----------------------------------------------------
    @DisplayName("обновить 1 комментарий")
    @Test
    public void shouldUpdateComment() {
        Comment commentBeforeEntityManager = emTest.find(Comment.class, 1l);
        String commentBeforeEntity = commentBeforeEntityManager.getTextComment();
        System.out.println("comment before:" + commentBeforeEntity);
        emTest.detach(commentBeforeEntityManager);
        repoComments.updateComment(UPDATE_NEW_COMMENT, 1);
        Comment commentAfterEntityManager = emTest.find(Comment.class, 1l);
        String commentAfterEntity = commentAfterEntityManager.getTextComment();
        assertEquals(commentAfterEntity, UPDATE_NEW_COMMENT);
    }

    //-----------------delete 1 comment----------------------------------------------------
    @DisplayName("удалить только 1 комментарий")
    @Test
    public void shouldDeleteComment() {
        List<Comment> bookListComment = (List<Comment>) repoComments.findAll();
        int sizeBefore = bookListComment.size();
        repoComments.deleteById(DELETE_BY_ID);
        List<Comment> bookListAfter = (List<Comment>) repoComments.findAll();
        int sizeAfter = bookListAfter.size();
        assertEquals(sizeBefore - sizeAfter, DELETE_SIZE);
    }
}
