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

@Service
public class BooksServiceImpl implements BooksService {

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

        System.out.println("123");

        List<Book> listBooks = repoBook.findAll();
        if (listBooks != null)
            listBooks.toString();

        System.out.println(listBooks);
        return listBooks;
    }

    @Override
    public Optional<Book> findBookById(long id) {
        Optional<Book> book = repoBook.findById(id);
        if (book.isPresent())
            book.toString();

        return book;
    }

    @Override
    public Optional<Book> findBookByName(String nameBook) {
        Optional<Book> book = repoBook.findByName(nameBook);
        if (book.isPresent())
            book.toString();

        return book;
    }

    @Override
    @Transactional
    public void deleteBookById(long id) {
        repoBook.deleteBookById(id);
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
        book.toString();
    }
}
