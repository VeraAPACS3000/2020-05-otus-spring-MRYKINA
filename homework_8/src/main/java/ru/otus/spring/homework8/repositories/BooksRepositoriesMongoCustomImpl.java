package ru.otus.spring.homework8.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import ru.otus.spring.homework8.model.Book;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;
import static org.springframework.data.mongodb.core.query.Criteria.where;

@Repository
public class BooksRepositoriesMongoCustomImpl implements BooksRepositoriesMongoCustom {

    @Autowired
    private final MongoTemplate mongoTemplate;

    public BooksRepositoriesMongoCustomImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    class ArraySizeProjection {
        private int size;

        public int getSize() {
            return size;
        }
    }

    @Override
    public void updateStatus(String name, int status) {
        Book book = mongoTemplate.findOne(
                Query.query(Criteria.where("name").is(name)), Book.class);
        book.setStatus(status);
        mongoTemplate.save(book, "books");
    }

    @Override
    public int getCommentsArrayLengthByBook(String nameBook) {
        Aggregation aggregation = Aggregation.newAggregation(
                match(where("name").is(nameBook)),
                project().andExclude("_id").and("comments").size().as("size"));

        ArraySizeProjection arraySizeProjection = mongoTemplate.aggregate(aggregation, Book.class, ArraySizeProjection.class).getUniqueMappedResult();
        return arraySizeProjection == null ? 0 : arraySizeProjection.getSize();
    }

    @Override
    public void deleteBookByName(String name) {
        Book book = mongoTemplate.findOne(
                Query.query(Criteria.where("name").is(name)), Book.class);
        mongoTemplate.remove(book);
    }

    @Override
    public void insertBookWithoutDuplicateName(Book book) {
        Book bookFind = mongoTemplate.findOne(
                Query.query(Criteria.where("name").is(book.getName())), Book.class);
        if (bookFind != null) {
            return;
        }
        mongoTemplate.insert(book, "books");
    }
}
