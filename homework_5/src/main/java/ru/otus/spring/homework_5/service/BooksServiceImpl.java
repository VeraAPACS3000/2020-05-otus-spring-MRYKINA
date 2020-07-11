package ru.otus.spring.homework_5.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.homework_5.domain.Books;
import ru.otus.spring.homework_5.dao.BookDao;

import java.util.List;

@Service
public class BooksServiceImpl implements BooksService {

    BookDao bookDao;

    BooksServiceImpl(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public List<Books> getAllBooks() {
        List<Books> listBooks = bookDao.getAll();
        for (Books books : listBooks) {
            System.out.println(books.toString());
        }
        return listBooks;
    }

    @Override
    public Books findBookById(long id) {
        return bookDao.findById(id);
    }

    @Override
    public void insertNewBook(Books books) {
        bookDao.insert(books);
    }

    @Override
    public void deleteBookById(long id) {
        bookDao.deleteById(1);
    }

    @Override
    public void updateStatusBook(long id, int status) {
        bookDao.updateByStatus(id, status);
    }
}
