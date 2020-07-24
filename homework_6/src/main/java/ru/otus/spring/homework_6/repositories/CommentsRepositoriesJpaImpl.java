package ru.otus.spring.homework_6.repositories;

import org.springframework.stereotype.Repository;
import ru.otus.spring.homework_6.models.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CommentsRepositoriesJpaImpl implements CommentsRepositoriesJpa {

    @PersistenceContext
    EntityManager em;

    @Override
    public List<Comment> getAll() {
        String jpql = "select c from Comment c";
        TypedQuery query = em.createQuery(jpql, Comment.class);
        return query.getResultList();
    }

    @Override
    public void deleteById(long id) {
        String jpql = "delete from Comment where id = :id";
        Query query = em.createQuery(jpql);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public void updateComment(long id, String newComment) {
        String jpql = "update Comment c set c.textComment = : newComment where c.id = :id";
        Query query = em.createQuery(jpql);
        query.setParameter("id", id);
        query.setParameter("newComment", newComment);
        query.executeUpdate();
    }

    @Override
    public Comment insert(Comment comment) {
        if (comment.getId() <= 0) {
            em.persist(comment);
            return comment;
        } else {
            return em.merge(comment);
        }
    }
}
