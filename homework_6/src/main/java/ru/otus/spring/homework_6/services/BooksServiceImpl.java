package ru.otus.spring.homework_6.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.homework_6.models.Author;
import ru.otus.spring.homework_6.models.Book;
import ru.otus.spring.homework_6.models.Genre;
import ru.otus.spring.homework_6.repositories.AuthorRepositoriesJpa;
import ru.otus.spring.homework_6.repositories.BooksRepositoriesJpa;
import ru.otus.spring.homework_6.repositories.CommentsRepositoriesJpa;
import ru.otus.spring.homework_6.repositories.GenreRepositoriesJpa;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Service
public class BooksServiceImpl implements BooksService {

    BooksRepositoriesJpa repositoriesBooks;
    CommentsRepositoriesJpa repositoriesComments;
    AuthorRepositoriesJpa authorRepositoriesJpa;
    GenreRepositoriesJpa genreRepositoriesJpa;
    EntityManager em;

    public BooksServiceImpl(BooksRepositoriesJpa repositoriesBooks, CommentsRepositoriesJpa repositoriesComments,
                            AuthorRepositoriesJpa authorRepositoriesJpa, GenreRepositoriesJpa genreRepositoriesJpa,
                            EntityManager em) {
        this.repositoriesComments = repositoriesComments;
        this.repositoriesBooks = repositoriesBooks;
        this.authorRepositoriesJpa = authorRepositoriesJpa;
        this.genreRepositoriesJpa = genreRepositoriesJpa;
        this.em = em;
    }

    @Override
    @Transactional
    public List<Book> getAllBooks() {
        List<Book> bookList = repositoriesBooks.getAll();
        for (Book book : bookList) {
            System.out.println(book.toString());
        }
        return bookList;
    }

    @Override
    @Transactional
    public Optional<Book> findBookById(long id) {
        Optional<Book> book = repositoriesBooks.findById(id);
        System.out.println(book.get().toString());
        return book;
    }

    @Override
    @Transactional
    public Book findBookByName(String nameBook) {
        Book book = null;
        if (repositoriesBooks.findByName(nameBook) != null) {
            book = repositoriesBooks.findByName(nameBook);
            System.out.println(book.toString());
        } else {
            System.out.println("not found the book");
        }
        return book;
    }

    @Override
    @Transactional
    public void deleteBookById(long id) {
        Optional<Book> book = repositoriesBooks.findById(id);
        if (book.isPresent())
            repositoriesBooks.delete(book.get());
        else
            System.out.println("nothing to delete");
    }

    @Override
    @Transactional
    public void addNewBook(String nameBook, String nameAuthor, String nameGenre) {
        Author author = new Author(nameAuthor);
        Genre genre = new Genre(nameGenre);
        Author authorFound = authorRepositoriesJpa.findAuthorByName(nameAuthor);
        Genre genreFound = genreRepositoriesJpa.findGenreByName(nameGenre);
        Book book = new Book(nameBook, author, genre);
        repositoriesBooks.insert(book);
    }

    @Transactional
    public void updateStatusBook(long id, int status) {
        Book book = em.find(Book.class, id);
        book.setStatus(status);
        em.merge(book);
    }

}
