package ru.otus.spring.homework_5.dao.extractor;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import ru.otus.spring.homework_5.domain.Authors;
import ru.otus.spring.homework_5.domain.Books;
import ru.otus.spring.homework_5.domain.Genres;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookResultSetExtractor implements ResultSetExtractor<List<Books>> {

    @Override
    public List<Books> extractData(ResultSet rs) throws SQLException, DataAccessException {
        Map<Long, Books> mapBook = new HashMap<>();
        Books books;
        while (rs.next()) {
            long id = rs.getLong("id");
            books = mapBook.get(id);
            if (books == null) {
                books = new Books(
                        id,
                        rs.getString("name"),
                        rs.getInt("status"),
                        new Authors(rs.getLong("id_author"), rs.getString("author_name")),
                        new Genres(rs.getLong("id_genre"), rs.getString("genre_name")));
                mapBook.put(id, books);
            }
        }
        return new ArrayList<>(mapBook.values());
    }
}
