package ru.otus.spring.homework_7.repositories;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.otus.spring.homework_7.models.Comment;

import java.util.List;

@Repository
public interface CommentsRepositoriesJpa extends CrudRepository<Comment, Long>, CommentsRepositoriesJpaCustom {

    List<Comment> findByNameBook(String name);

    List<Comment> findByIdBook(long id);

    void updateComment(String text, long id);

    @Override
    @EntityGraph(value = "comment-books-entity-graph")
    List<Comment> findAll();

}
