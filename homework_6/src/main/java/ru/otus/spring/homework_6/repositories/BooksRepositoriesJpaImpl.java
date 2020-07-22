package ru.otus.spring.homework_6.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.otus.spring.homework_6.models.Author;
import ru.otus.spring.homework_6.models.Book;
import ru.otus.spring.homework_6.models.Genre;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.ofNullable;


@Repository
public class BooksRepositoriesJpaImpl implements BooksRepositoriesJpa {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    AuthorRepositoriesJpa authorRepositoriesJpa;

    @Override
    public List<Book> getAll() {
        String jpql = "select b from Book b";
        return em.createQuery(jpql, Book.class).getResultList();
    }

    @Override
    public Optional<Book> findById(long id) {
        return ofNullable(em.find(Book.class, id));
    }

    @Override
    public Book findByName(String name) {
        Book book = null;
        try {
            String jpql = "select b from Book b where b.name = :name";
            TypedQuery query = em.createQuery(jpql, Book.class);
            query.setParameter("name", name);
            book = (Book) query.getSingleResult();
        } catch (NoResultException ex) {
            System.out.println("not found book by name");
        }
        return book;
    }

    @Override
    public Book insert(Book book) {
        if (book.getId() <= 0) {
            em.persist(book);
            return book;
        } else {
            return em.merge(book);
        }
    }

    @Override
    public void delete(Book book) {
        em.remove(book);
    }
}
