package ru.otus.spring.homework_7.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import ru.otus.spring.homework_7.models.Book;
import ru.otus.spring.homework_7.models.Comment;

import java.util.List;
import java.util.Optional;


public class CommentsRepositoriesJpaCustomImpl implements CommentsRepositoriesJpaCustom {

    @Autowired
    BooksRepositoriesJpa repoBook;

    @Autowired
    CommentsRepositoriesJpa repoComment;

    CommentsRepositoriesJpaCustomImpl(BooksRepositoriesJpa repoBook, CommentsRepositoriesJpa repoComment) {
        this.repoBook = repoBook;
        this.repoComment = repoComment;
    }

    public List<Comment> findByNameBook(String nameBook) {
        Optional<Book> book = repoBook.findByName(nameBook);
        List<Comment> commentList = null;
        int count = 0;
        if (book.isPresent()) {
            commentList = book.get().getComments();
            for (Comment comment : commentList) {
                System.out.println(++count + ")" + comment.toString());
            }
        } else {
            System.out.println("Oops...No comments yet");
        }
        return commentList;
    }

    public void updateComment(String textComment, long id) {
        Optional<Comment> comment = repoComment.findById(id);
        if (comment.isPresent()) {
            comment.get().setTextComment(textComment);
            repoComment.save(comment.get());
        } else {
            System.out.println("Not found comment to update");
        }
    }

    @Override
    public List<Comment> findByIdBook(long id) {
        System.out.println("раз");
        int count = 0;
        List<Comment> commentList = null;
        Optional<Book> book = repoBook.findById(id);
        if (book.isPresent()) {
            commentList = book.get().getComments();
            for (Comment comment : commentList) {
                System.out.println(++count + ")" + comment.toString());
            }
        } else {
            System.out.println("Oops...No comments yet");
        }
        return commentList;
    }
}
