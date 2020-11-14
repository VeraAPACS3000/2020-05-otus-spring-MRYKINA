package ru.otus.spring.homework11.changelogs;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import ru.otus.spring.homework11.models.Author;
import ru.otus.spring.homework11.models.Book;
import ru.otus.spring.homework11.models.Comment;
import ru.otus.spring.homework11.models.Genre;
import ru.otus.spring.homework11.repositoriesnonreact.AuthorsRepositoriesNonReact;
import ru.otus.spring.homework11.repositoriesnonreact.BooksRepositoriesNonReact;
import ru.otus.spring.homework11.repositoriesnonreact.CommentsRepositpriesNonReact;
import ru.otus.spring.homework11.repositoriesnonreact.GenresRepositoriesNonReact;


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
    public void initComments(CommentsRepositpriesNonReact repository) {
        System.out.println("initComments");
        comment1 = repository.save(new Comment("the best book!"));
        comment2 = repository.save(new Comment("Good book!"));
        comment3 = repository.save(new Comment("Big book!"));
        comment4 = repository.save(new Comment("Super book!"));
        comment5 = repository.save(new Comment("yes, good book"));
    }

    @ChangeSet(order = "002", id = "initAuthors", author = "mymongock", runAlways = true)
    public void initAuthors(AuthorsRepositoriesNonReact repository) {
        author1 = repository.save(new Author("Lev Tolstoy"));
        author2 = repository.save(new Author("Dostoevskij"));

    }

    @ChangeSet(order = "003", id = "initGenres", author = "mymongock", runAlways = true)
    public void initGenres(GenresRepositoriesNonReact repository) {
        genre1 = repository.save(new Genre("Roman"));
    }

    @ChangeSet(order = "004", id = "initBooks", author = "mymongock", runAlways = true)
    public void initBooks(BooksRepositoriesNonReact repository) {
        repository.save(new Book("Vojna i mir", 1, author1, genre1, comment1, comment2));
        repository.save(new Book("Besi", 1, author2, genre1, comment3));
        repository.save(new Book("Anna Karenina", 1, author1, genre1, comment4));
        repository.save(new Book("Igrok", 0, author2, genre1, comment5));
    }
}
