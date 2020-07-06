package ru.otus.spring.homework_5.dao.extractor;

import org.springframework.jdbc.core.RowMapper;
import ru.otus.spring.homework_5.domain.Authors;
import ru.otus.spring.homework_5.domain.Books;
import ru.otus.spring.homework_5.domain.Genres;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Books> {
    @Override
    public Books mapRow(ResultSet resultSet, int i) throws SQLException {

        System.out.println("QUERY" + resultSet.toString());

        long id = resultSet.getLong("id");
        String name = resultSet.getString("name");
        int status = resultSet.getInt("status");
        Authors authors = new Authors(resultSet.getLong("id_author"), resultSet.getString("author_name"));
        Genres genres = new Genres(resultSet.getLong("id_genre"), resultSet.getString("genre_name"));
        return new Books(id, name, status, authors, genres);
    }
}
