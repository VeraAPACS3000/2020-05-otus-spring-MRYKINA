package ru.otus.spring.homework7.services;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.homework7.models.Book;
import ru.otus.spring.homework7.models.Comment;
import ru.otus.spring.homework7.repositories.BooksRepositoriesJpa;
import ru.otus.spring.homework7.repositories.CommentsRepositoriesJpa;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class CommentsServiceImpl implements CommentsService {

    private static Logger log = Logger.getLogger(CommentsServiceImpl.class.getName());

    CommentsRepositoriesJpa repoComment;

    BooksRepositoriesJpa repoBook;

    //EntityManager em;

    CommentsServiceImpl(CommentsRepositoriesJpa repoComment, BooksRepositoriesJpa repoBook) {
        this.repoComment = repoComment;
        this.repoBook = repoBook;
    }

    @Override
    public Optional<Comment> findCommentById(Long id) {
        Optional<Comment> comment = repoComment.findById(id);
        if (comment == null) {
            log.info("Not found comment by id");
        }
        return comment;
    }

    @Override
    public List<Comment> findCommentByIdBook(long idBook) {
        List<Comment> commentList = repoComment.findByIdBook(idBook);
        if (commentList == null) {
            log.info("Not found list comments by id book");
        }
        return commentList;
    }

    @Override
    public List<Comment> findCommentsByNameBook(String nameBook) {
        List<Comment> commentList = repoComment.findByNameBook(nameBook);
        if (commentList == null) {
            log.info("Not found list comments by name book");
        }
        return commentList;
    }

    @Override
    public List<Comment> getAllComments() {
        List<Comment> commentList = repoComment.findAll();
        if (commentList == null) {
            log.info("Not found list comments");
        }
        return commentList;
    }

    @Override
    @Transactional
    public void deleteCommentByIdBook(long id) {
        repoComment.deleteById(id);
    }

    @Override
    @Modifying(clearAutomatically = true)
    @Transactional
    public void updateCommentById(long id, String newComment) {
        repoComment.updateComment(newComment, id);
    }

    @Override
    @Transactional
    public Comment addNewComment(String textComment, String nameBook) {
        Comment comment = null;
        Optional<Book> book = repoBook.findByName(nameBook);
        if (book.isPresent()) {
            comment = new Comment(book.get().getId(), textComment);
            repoComment.save(comment);
        } else {
            log.info("Oops..no the book for your comment");
        }
        return comment;
    }
}
