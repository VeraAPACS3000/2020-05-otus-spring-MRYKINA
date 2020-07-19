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
    public List<Comment> findByIdBook(long idBook) {
        String jpql = "select c from Comment c where c.id_books = :idBook";
        TypedQuery query = em.createQuery(jpql, Comment.class);
        query.setParameter("idBook", idBook);
        return query.getResultList();
    }

    @Override
    public List<Comment> findByNameBook(String nameBook) {
        String jpql = "select c from Comment c where c.id_books in (select b.id from Book b where b.name = :name)";
        TypedQuery query = em.createQuery(jpql, Comment.class);
        query.setParameter("name", nameBook);
        return query.getResultList();
    }

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
        String jpql = "update Comment set text_comment = : newComment where id = :id";
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
