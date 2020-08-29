package ru.otus.spring.homework8.changelogs;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import ru.otus.spring.homework8.model.Author;
import ru.otus.spring.homework8.model.Book;
import ru.otus.spring.homework8.model.Comment;
import ru.otus.spring.homework8.model.Genre;
import ru.otus.spring.homework8.repositories.AuthorsRepositoriesMongo;
import ru.otus.spring.homework8.repositories.BooksRepositoriesMongo;
import ru.otus.spring.homework8.repositories.CommentsRepositpriesMongo;
import ru.otus.spring.homework8.repositories.GenresRepositoriesMongo;

@ChangeLog(order = "001")
public class ChangeLogs {

    private Comment comment1;
    private Comment comment2;
    private Comment comment3;
    private Comment comment4;
    private Comment comment5;

    private Author author1;
    private Author author2;

    private Genre genre1;

    @ChangeSet(order = "000", id = "dropDB", author = "mymongock", runAlways = true)
    public void dropDB(MongoDatabase database) {
        database.drop();
    }

    @ChangeSet(order = "001", id = "initComments", author = "mymongock", runAlways = true)
    public void initComments(CommentsRepositpriesMongo repository) {
        comment1 = repository.save(new Comment("the best book!"));
        comment2 = repository.save(new Comment("Good book!"));
        comment3 = repository.save(new Comment("Big book!"));
        comment4 = repository.save(new Comment("Super book!"));
        comment5 = repository.save(new Comment("yes, good book"));
    }

    @ChangeSet(order = "002", id = "initAuthors", author = "mymongock", runAlways = true)
    public void initAuthors(AuthorsRepositoriesMongo repository) {
        author1 = repository.save(new Author("Lev Tolstoy"));
        author2 = repository.save(new Author("Dostoevskij"));

    }

    @ChangeSet(order = "003", id = "initGenres", author = "mymongock", runAlways = true)
    public void initGenres(GenresRepositoriesMongo repository) {
        genre1 = repository.save(new Genre("Roman"));
    }

    @ChangeSet(order = "004", id = "initBooks", author = "mymongock", runAlways = true)
    public void initBooks(BooksRepositoriesMongo repository) {
        repository.save(new Book("Vojna i mir", 1, author1, genre1, comment1, comment2));
        repository.save(new Book("Besi", 1, author2, genre1, comment3));
        repository.save(new Book("Anna Karenina", 1, author1, genre1, comment4));
        repository.save(new Book("Igrok", 0, author2, genre1, comment5));
    }
}
