package ru.otus.spring.homework_7.services;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.homework_7.models.Author;
import ru.otus.spring.homework_7.models.Book;
import ru.otus.spring.homework_7.models.Genre;
import ru.otus.spring.homework_7.repositories.AuthorRepositoriesJpa;
import ru.otus.spring.homework_7.repositories.BooksRepositoriesJpa;
import ru.otus.spring.homework_7.repositories.GenreRepositoriesJpa;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class BooksServiceImpl implements BooksService {

    private static Logger log = Logger.getLogger(BooksServiceImpl.class.getName());

    BooksRepositoriesJpa repoBook;

    AuthorRepositoriesJpa repoAuthor;

    GenreRepositoriesJpa repoGenre;

    BooksServiceImpl(BooksRepositoriesJpa repoBook, AuthorRepositoriesJpa repoAuthor, GenreRepositoriesJpa repoGenre) {
        this.repoBook = repoBook;
        this.repoAuthor = repoAuthor;
        this.repoGenre = repoGenre;
    }

    @Override
    public List<Book> getAllBooks() {
        List<Book> listBooks = (List<Book>) repoBook.findAll();
        if (listBooks == null) {
            log.info("Not found list books");
        }
        return listBooks;
    }

    @Override
    public Optional<Book> findBookById(long id) {
        Optional<Book> bookOptional = repoBook.findById(id);
        if (bookOptional == null) {
            log.info("Not found book by id");
        }
        return repoBook.findById(id);
    }

    @Override
    public Optional<Book> findBookByName(String nameBook) {
        Optional<Book> bookOptional = repoBook.findByName(nameBook);
        if (bookOptional == null) {
            log.info("Not found book by name");
        }
        return bookOptional;
    }

    @Override
    @Transactional
    public void deleteBookById(long id) {
        repoBook.deleteById(id);
    }

    @Override
    @Modifying(clearAutomatically = true)
    @Transactional
    public void updateStatusBook(long id, int status) {
        repoBook.updateBookStatus(id, status);
    }

    @Override
    @Transactional
    public void addNewBook(String nameBook, String nameAuthor, String nameGenre) {
        Author author = new Author(nameAuthor);
        Genre genre = new Genre(nameGenre);
        Optional<Author> authorFound = repoAuthor.findByName(nameAuthor);
        Optional<Genre> genreFound = repoGenre.findByName(nameGenre);
        Book book = new Book(nameBook, author, genre);
        repoBook.save(book);
    }
}
