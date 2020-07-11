package ru.otus.spring.homework_5.dao;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.spring.homework_5.dao.extractor.BookMapper;
import ru.otus.spring.homework_5.dao.extractor.BookResultSetExtractor;
import ru.otus.spring.homework_5.domain.Books;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class BookDaoJdbc implements BookDao {

    NamedParameterJdbcOperations namedParameterJdbcOperations;

    public BookDaoJdbc(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public List<Books> getAll() {
        String sql = "select b.id, b.name, b.status, a.id id_author, a.name author_name, g.id id_genre, g.name genre_name " +
                "from (books b left join authors a on b.id_author = a.id) left join genres g on b.id_genre = g.id";
        return namedParameterJdbcOperations.query(sql, new BookResultSetExtractor());
    }

    @Override
    public Books findById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        String sql = "select b.id, b.name, b.status, a.id id_author, a.name author_name, g.id id_genre, g.name genre_name " +
                "from (books b left join authors a on b.id_author = a.id) left join genres g on b.id_genre = g.id where b.id = :id";
        return namedParameterJdbcOperations.queryForObject(sql, params, new BookMapper());
    }

    @Override
    public int insert(Books books) {
        /** в books (id, name, status, author, genre) здесь те названия, что в sql-файле
         * а вот эти values (:id, :name, :status, :authors, :genres) - из класса*/
        String sql = "insert into books (name, id_author, id_genre) values (:name, :authors.id, :genres.id)";
        return namedParameterJdbcOperations.update(sql, new BeanPropertySqlParameterSource(books));
    }

    @Override
    public int updateByStatus(long id, int status) {
        final MapSqlParameterSource parameters = new MapSqlParameterSource("id", id).addValue("status", status);
        return namedParameterJdbcOperations.update("update books set status = :status where id = :id", parameters);
    }

    @Override
    public int deleteById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.update("delete from books where id = :id", params);
    }
}
