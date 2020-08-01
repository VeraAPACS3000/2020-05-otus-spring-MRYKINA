package ru.otus.spring.homework7.repositories;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.otus.spring.homework7.models.Comment;

import java.util.List;

@Repository
public interface CommentsRepositoriesJpa extends CrudRepository<Comment, Long>, CommentsRepositoriesJpaCustom {

    void updateComment(String text, long id);

    @Override
    @EntityGraph(value = "comment-books-entity-graph")
    List<Comment> findAll();

}
