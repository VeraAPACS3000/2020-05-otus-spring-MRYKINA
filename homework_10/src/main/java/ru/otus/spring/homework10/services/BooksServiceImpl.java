package ru.otus.spring.homework10.services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.homework10.feign.MyFeignClient;
import ru.otus.spring.homework10.models.Author;
import ru.otus.spring.homework10.models.Book;
import ru.otus.spring.homework10.models.Comment;
import ru.otus.spring.homework10.models.Genre;
import ru.otus.spring.homework10.repositories.AuthorRepositoriesJpa;
import ru.otus.spring.homework10.repositories.BooksRepositoriesJpa;
import ru.otus.spring.homework10.repositories.GenreRepositoriesJpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class BooksServiceImpl implements BooksService {

    private static Logger log = Logger.getLogger(BooksServiceImpl.class.getName());

    private BooksRepositoriesJpa repoBook;

    private AuthorRepositoriesJpa repoAuthor;

    private GenreRepositoriesJpa repoGenre;

    @Autowired
    private MyFeignClient feignClient;

    BooksServiceImpl(BooksRepositoriesJpa repoBook, AuthorRepositoriesJpa repoAuthor, GenreRepositoriesJpa repoGenre) {
        this.repoBook = repoBook;
        this.repoAuthor = repoAuthor;
        this.repoGenre = repoGenre;
    }

    @Override
    @Transactional(readOnly = true)
    @HystrixCommand(commandKey = "getBooksKeys", fallbackMethod = "buildFallBackBooks")
    public List<Book> getAllBooks() {
        List<Book> listBooks = (List<Book>) repoBook.findAll();
        if (listBooks == null) {
            log.info("Not found list books");
        }
        return listBooks;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Book> findBookById(long id) {
        Optional<Book> bookOptional = repoBook.findById(id);
        if (bookOptional == null) {
            log.info("Not found book by id");
        }
        return repoBook.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
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
        status = feignClient.getNewStatus();
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

    public List<Book> buildFallBackBooks() {
        List<Book> listBooks = new ArrayList<Book>();
        Comment c = new Comment(0l, "N/A");
        List<Comment> l = new ArrayList<>();
        l.add(c);
        listBooks.add(new Book(0,
                "N/A", new Author("N/A"),
                new Genre("N/A"),
                l));
        return listBooks;
    }

    public Optional<Book> buildFallBackBooksOptional() {
        Optional<Book> bookOptional =
                Optional.of(new Book("N/A", new Author("N/A"), new Genre("N/A")));
        return bookOptional;
    }
}

