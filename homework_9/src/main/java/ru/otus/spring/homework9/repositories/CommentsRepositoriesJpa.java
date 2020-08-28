package ru.otus.spring.homework9.repositories;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.otus.spring.homework9.models.Comment;

import java.util.List;

@Repository
public interface CommentsRepositoriesJpa extends CrudRepository<Comment, Long> {
    @Override
    @EntityGraph(value = "comment-books-entity-graph")
    List<Comment> findAll();
}
