package ru.otus.spring.homework_7.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.otus.spring.homework_7.models.Comment;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentsRepositoriesJpa extends CrudRepository<Comment, Long>, CommentsRepositoriesJpaCustom {

    @Override
    List<Comment> findAll();

    @Override
    Optional<Comment> findById(Long id);

    @Override
    void deleteById(Long id);

    @Override
    Comment save(Comment comment);

    @Override
    List<Comment> findByNameBook(String name);

    @Override
    List<Comment> findByIdBook(long id);

    @Override
    void updateComment(String text, long id);
}
