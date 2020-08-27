package ru.otus.spring.homework8.services;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import ru.otus.spring.homework8.model.Author;
import ru.otus.spring.homework8.model.Book;
import ru.otus.spring.homework8.model.Comment;
import ru.otus.spring.homework8.model.Genre;
import ru.otus.spring.homework8.repositories.AuthorsRepositoriesMongo;
import ru.otus.spring.homework8.repositories.BooksRepositoriesMongo;
import ru.otus.spring.homework8.repositories.CommentsRepositpriesMongo;
import ru.otus.spring.homework8.repositories.GenresRepositoriesMongo;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class BooksServicesImpl implements BooksServices {

    private static Logger log = Logger.getLogger(BooksServicesImpl.class.getName());
    private final MongoTemplate mongoTemplate;

    BooksRepositoriesMongo repoBook;
    CommentsRepositpriesMongo repoComments;
    AuthorsRepositoriesMongo repoAuthors;
    GenresRepositoriesMongo repoGenres;

    BooksServicesImpl(MongoTemplate mongoTemplate,
                      BooksRepositoriesMongo repoBook,
                      CommentsRepositpriesMongo repoComments,
                      AuthorsRepositoriesMongo repoAuthors,
                      GenresRepositoriesMongo repoGenres) {
        this.mongoTemplate = mongoTemplate;
        this.repoBook = repoBook;
        this.repoComments = repoComments;
        this.repoAuthors = repoAuthors;
        this.repoGenres = repoGenres;
    }

    @Override
    public List<Book> findAll() {
        return repoBook.findAll();
    }

    @Override
    public Book findByName(String name) {
        return repoBook.findByName(name);
    }

    @Override
    public Optional<Book> findById(String id) {
        return repoBook.findById(id);
    }

    @Override
    public void deleteByNameBook(String nameBook) {
        Book book = mongoTemplate.findOne(
                Query.query(Criteria.where("name").is(nameBook)), Book.class);
        mongoTemplate.remove(book);
    }

    @Override
    public void deleteByBook(Book book) {
        repoComments.deleteAll(book.getComments());
        repoBook.delete(book);
    }

    @Override
    public void add(String nameBook, String nameAuthor, String nameGenre) {
        Book bookFind = mongoTemplate.findOne(
                Query.query(Criteria.where("name").is(nameBook)), Book.class);
        if (bookFind == null) {
            Author author = new Author(nameAuthor);
            Genre genre = new Genre(nameGenre);
            Comment comment = new Comment();
            repoAuthors.insert(author);
            repoGenres.insert(genre);
            repoComments.insert(comment);
            Book book = new Book(nameBook, 1, author, genre, comment);
            mongoTemplate.insert(book, "books");
        }
    }

    @Override
    public void insert(Book book) {
        Book bookFind = mongoTemplate.findOne(
                Query.query(Criteria.where("name").is(book.getName())), Book.class);
        if (bookFind == null) {
            mongoTemplate.insert(book, "books");
        }
    }

    @Override
    public void update(String name, int status) {
        Book book = mongoTemplate.findOne(
                Query.query(Criteria.where("name").is(name)), Book.class);
        book.setStatus(status);
        mongoTemplate.save(book, "books");
    }

    @Override
    public int getCountCommentsAboutBook(String name) {
        return repoBook.getCommentsArrayLengthByBook(name);
    }
}
