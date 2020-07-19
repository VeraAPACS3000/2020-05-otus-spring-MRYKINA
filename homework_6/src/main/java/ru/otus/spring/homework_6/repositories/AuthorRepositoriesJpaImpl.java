package ru.otus.spring.homework_6.repositories;

import org.springframework.stereotype.Repository;
import ru.otus.spring.homework_6.models.Author;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Repository
public class AuthorRepositoriesJpaImpl implements AuthorRepositoriesJpa {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Author findAuthorByName(String name) {
        Author author = null;
        try {
            String jpql = "select a from Author a where a.name = :name";
            TypedQuery query = em.createQuery(jpql, Author.class);
            query.setParameter("name", name);
            author = (Author) query.getSingleResult();
            return author;
        } catch (NoResultException ex) {
            System.out.println("not found author by name");
        }
        return author;
    }
}
