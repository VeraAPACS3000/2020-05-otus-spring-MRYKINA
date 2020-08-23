package ru.otus.spring.homework8.services;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.spring.homework8.model.Comment;
import ru.otus.spring.homework8.repositories.CommentsRepositpriesMongo;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DisplayName("CommentsServices должен ")
public class CommentsServicesTest {

    @Autowired
    CommentsServices serviceComments;

    @Autowired
    CommentsRepositpriesMongo repoComments;

    @DisplayName("возвращать список всех комментариев")
    @Test
    void shouldReturnListComments() {
        List<Comment> listComments = serviceComments.findAllComments();
        assertThat(listComments).isNotNull();
    }

    @DisplayName("возвращать ожидаемое количество комментариев")
    @Test
    void shouldReturnCorrectListComments() {
        List<Comment> listComments = serviceComments.findAllComments();
        int sizeCommentListBefore = listComments.size();
        int sizeCommentListAfter = serviceComments.countAllComments();
        assertThat(sizeCommentListBefore).isEqualTo(sizeCommentListAfter);
    }

    @DisplayName("добавить комментарий")
    @Test
    void shouldAddComment() {
        int sizeCommentListBefore = serviceComments.countAllComments();
        serviceComments.add("text best comment");
        int sizeCommentListAfter = serviceComments.countAllComments();
        assertThat(sizeCommentListAfter).isEqualTo(sizeCommentListBefore + 1);
    }
}
