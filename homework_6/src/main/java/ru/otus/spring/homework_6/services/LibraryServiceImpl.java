package ru.otus.spring.homework_6.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.homework_6.models.Author;
import ru.otus.spring.homework_6.models.Book;
import ru.otus.spring.homework_6.models.Comment;
import ru.otus.spring.homework_6.models.Genre;
import ru.otus.spring.homework_6.repositories.AuthorRepositoriesJpa;
import ru.otus.spring.homework_6.repositories.BooksRepositoriesJpa;
import ru.otus.spring.homework_6.repositories.CommentsRepositoriesJpa;
import ru.otus.spring.homework_6.repositories.GenreRepositoriesJpa;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Service
public class LibraryServiceImpl implements LibraryService {

    BooksRepositoriesJpa repositoriesBooks;
    CommentsRepositoriesJpa repositoriesComments;
    AuthorRepositoriesJpa authorRepositoriesJpa;
    GenreRepositoriesJpa genreRepositoriesJpa;
    EntityManager em;

    public LibraryServiceImpl(BooksRepositoriesJpa repositoriesBooks, CommentsRepositoriesJpa repositoriesComments,
                              AuthorRepositoriesJpa authorRepositoriesJpa, GenreRepositoriesJpa genreRepositoriesJpa,
                              EntityManager em) {
        this.repositoriesComments = repositoriesComments;
        this.repositoriesBooks = repositoriesBooks;
        this.authorRepositoriesJpa = authorRepositoriesJpa;
        this.genreRepositoriesJpa = genreRepositoriesJpa;
        this.em = em;
    }

    //Book services
    @Override
    @Transactional(readOnly = true)
    public List<Book> getAllBooks() {
        List<Book> bookList = repositoriesBooks.getAll();
        System.out.println("SIZE:" + bookList.size());
        for (Book book : bookList) {
            System.out.println(book.toString());
        }
        return bookList;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Book> findBookById(long id) {
        Optional<Book> book = repositoriesBooks.findById(id);
        System.out.println(book.get().toString());
        return book;
    }

    @Override
    @Transactional(readOnly = true)
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
        repositoriesBooks.deleteById(id);
    }

    @Override
    @Transactional
    public void updateStatusBook(long id, int status) {
        repositoriesBooks.updateStatus(id, status);
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

    //Comment services
    @Override
    @Transactional(readOnly = true)
    public List<Comment> findCommentByIdBook(long idBook) {
        int count = 0;
        List<Comment> commentList = repositoriesComments.findByIdBook(idBook);
        if (commentList.isEmpty()) {
            System.out.println("Oops...No comments yet");
            return commentList;
        } else {
            for (Comment comment : commentList) {
                System.out.println(++count + ")" + comment.toString());
            }
        }
        return commentList;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comment> findCommentsByNameBook(String nameBook) {
        List<Comment> commentList = repositoriesComments.findByNameBook(nameBook);
        if (commentList.isEmpty()) {
            System.out.println("Oops...No comments yet");
            return commentList;
        } else {
            for (Comment comment : commentList) {
                System.out.println(comment.toString());
            }
        }
        return commentList;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comment> getAllComments() {
        List<Comment> commentList = repositoriesComments.getAll();
        for (Comment comment : commentList) {
            System.out.println(comment.toString());
        }
        return repositoriesComments.getAll();
    }

    @Override
    @Transactional
    public void deleteCommentByIdBook(long id) {
        repositoriesComments.deleteById(id);
    }

    @Override
    @Transactional
    public void updateCommentById(long id, String newComment) {
        repositoriesComments.updateComment(id, newComment);
    }

    @Override
    @Transactional
    public Comment addNewComment(String textComment, String nameBook) {
        Comment comment = null;
        Book book = repositoriesBooks.findByName(nameBook);
        if (book != null) {
            comment = new Comment(book.getId(), textComment);
            repositoriesComments.insert(comment);
        } else {
            System.out.println("Oops..no the book for your comment");
        }
        return comment;
    }
}
