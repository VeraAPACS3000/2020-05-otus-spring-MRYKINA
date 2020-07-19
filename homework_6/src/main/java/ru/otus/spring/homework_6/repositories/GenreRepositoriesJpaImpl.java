package ru.otus.spring.homework_6.repositories;

import org.springframework.stereotype.Repository;
import ru.otus.spring.homework_6.models.Genre;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Repository
public class GenreRepositoriesJpaImpl implements GenreRepositoriesJpa {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Genre findGenreByName(String name) {
        Genre genre = null;
        try {
            String jpql = "select g from Genre g where g.name = :name";
            TypedQuery query = em.createQuery(jpql, Genre.class);
            query.setParameter("name", name);
            genre = (Genre) query.getSingleResult();
        } catch (NoResultException ex) {
            System.out.println("not found genre by name");
        }
        return genre;
    }
}
