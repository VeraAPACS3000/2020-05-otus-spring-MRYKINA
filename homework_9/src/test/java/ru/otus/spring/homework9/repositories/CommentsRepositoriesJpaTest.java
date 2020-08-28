package ru.otus.spring.homework9.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;
import ru.otus.spring.homework9.models.Comment;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@DisplayName("Репозиторий для работы с комментариями должен")
@DataJpaTest
public class CommentsRepositoriesJpaTest {

    private static final int ALL_COMMENTS_SIZE = 20;
    private static final String INSERT_NEW_COMMENT = "new best comment test";
    private static final long DELETE_BY_ID = 2l;
    private static final long DELETE_SIZE = 1l;
    private static final long FIND_BY_ID_COMMENT = 8l;


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

    //-------------------insert 1 comment---------------------------------------------------
    @DisplayName("добавить 1 новый комментарий не NULL")
    @Test
    public void shouldInsertNewCommentNotNull() {
        Comment comment = new Comment(1, INSERT_NEW_COMMENT);
        repoComments.save(comment);
        assertThat(emTest.find(Comment.class, 1l)).isNotNull();
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
