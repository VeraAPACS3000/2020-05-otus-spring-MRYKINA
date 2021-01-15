package ru.otus.spring.homework14;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.data.builder.MongoItemWriterBuilder;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.otus.spring.homework14.configuration.models.*;
import ru.otus.spring.homework14.services.ConvertToMongo;
import ru.otus.spring.homework14.models.*;

import javax.persistence.EntityManager;

@SuppressWarnings("all")


@EnableBatchProcessing
@Configuration
public class JobConfiguration {

    public static final String IMPORT_DB = "importDbSqlToMongoDB";

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Bean
    public Step step1(MongoItemWriter writer, JpaPagingItemReader reader, ItemProcessor processor) {
        return stepBuilderFactory.get("step1Author")
                .chunk(2)//кол-во записей за раз
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    @Bean
    public Step step2(MongoItemWriter writerGenre, JpaPagingItemReader readerGenre, ItemProcessor processorGenre) {
        return stepBuilderFactory.get("step2Genre")
                .chunk(2)//кол-во записей за раз
                .reader(readerGenre)
                .processor(processorGenre)
                .writer(writerGenre)
                .build();
    }

    @Bean
    public Step step3(MongoItemWriter writerComment, JpaPagingItemReader readerComment, ItemProcessor processorComment) {
        return stepBuilderFactory.get("step3Comment")
                .chunk(2)//кол-во записей за раз
                .reader(readerComment)
                .processor(processorComment)
                .writer(writerComment)
                .build();
    }
    @Bean
    public Step step4(MongoItemWriter writerBook, JpaPagingItemReader readerBook, ItemProcessor processorBook) {
        return stepBuilderFactory.get("step4Book")
                .chunk(3)//кол-во записей за раз
                .reader(readerBook)
                .processor(processorBook)
                .writer(writerBook)
                .build();
    }

    /**
     * ==========================JOB======================================
     */
    @Bean
    public Job importDbSqlToMongoDB(Step step1, Step step2, Step step3, Step step4) {
        return jobBuilderFactory.get(IMPORT_DB)
                .incrementer(new RunIdIncrementer())
                .start(step1)
                .next(step2)
                .next(step3)
                .next(step4)
                //.end()
                .build();
    }

    /**
     * ===============readers===========================================
     */
    @StepScope
    @Bean
    public JpaPagingItemReader<Author> reader(EntityManager em) throws Exception {
        return new JpaPagingItemReaderBuilder<Author>()
                .name("authorItemReader")
                .entityManagerFactory(em.getEntityManagerFactory())//что будем читать
                .queryString("Select e from Author e")
                .pageSize(1)
                .build();
    }

    @StepScope
    @Bean
    public JpaPagingItemReader<Genre> readerGenre(EntityManager em) throws Exception {
        return new JpaPagingItemReaderBuilder<Genre>()
                .name("genreItemReader")
                .entityManagerFactory(em.getEntityManagerFactory())//что будем читать
                .queryString("Select e from Genre e")
                .pageSize(1)
                .build();
    }

    @StepScope
    @Bean
    public JpaPagingItemReader<Comment> readerComment(EntityManager em) throws Exception {
        return new JpaPagingItemReaderBuilder<Comment>()
                .name("commentItemReader")
                .entityManagerFactory(em.getEntityManagerFactory())//что будем читать
                .queryString("Select e from Comment e")
                .pageSize(1)
                .build();
    }

    @StepScope
    @Bean
    public JpaPagingItemReader<Book> readerBook(EntityManager em) throws Exception {
        return new JpaPagingItemReaderBuilder<Book>()
                .name("bookItemReader")
                .entityManagerFactory(em.getEntityManagerFactory())
                //что будем читать
                .queryString("Select b from Book b")
                .pageSize(1)
                .build();
    }

    /**
     * ===============processors===================================
     */
    @StepScope
    @Bean
    public ItemProcessor processor(ConvertToMongo convert) {
        return (ItemProcessor<Author, AuthorMongo>) convert::doConvert;
    }

    @StepScope
    @Bean
    public ItemProcessor processorGenre(ConvertToMongo convert) {
        return (ItemProcessor<Genre, GenreMongo>) convert::doConvert;
    }

    @StepScope
    @Bean
    public ItemProcessor processorComment(ConvertToMongo convert) {
        return (ItemProcessor<Comment, CommentMongo>) convert::doConvert;
    }
    @StepScope
    @Bean
    public ItemProcessor processorBook(ConvertToMongo convert) {
        return (ItemProcessor<Book, BookMongo>) convert::doConvert;
    }

    /**
     * ===============writers======================================
     */
    @StepScope
    @Bean
    public MongoItemWriter writer(MongoTemplate template) {
        return new MongoItemWriterBuilder<AuthorMongo>()
                .template(template)
                .collection("authors")
                .build();
    }

    @StepScope
    @Bean
    public MongoItemWriter writerGenre(MongoTemplate template) {
        return new MongoItemWriterBuilder<GenreMongo>()
                .template(template)
                .collection("genres")
                .build();
    }

    @StepScope
    @Bean
    public MongoItemWriter writerComment(MongoTemplate template) {
        return new MongoItemWriterBuilder<CommentMongo>()
                .template(template)
                .collection("comments")
                .build();
    }

    @StepScope
    @Bean
    public MongoItemWriter writerBook(MongoTemplate template) {
        return new MongoItemWriterBuilder<BookMongo>()
                .template(template)
                .collection("books")
                .build();
    }

   /* @StepScope
    @Bean
    public FlatFileItemWriter writer() {
        return new FlatFileItemWriterBuilder<>()
                .name("personItemWriter")
                .resource(new FileSystemResource(appProps.getOutputFile()))
                .lineAggregator(new DelimitedLineAggregator<>())
                .build();
    }*/
}
