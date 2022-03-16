package fr.geromeavecung.dddsample;

import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.authors.AddAnAuthor;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.authors.Authors;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.authors.ReadAuthors;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.authors.ReadOneAuthor;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.books.AddABook;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.books.ListAllBooks;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core.Identifiers;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.books.Books;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.books.ListBookDetails;
import fr.geromeavecung.dddsample.businessdomain.usecases.authorsusecases.ALibrarianAddsAnAuthor;
import fr.geromeavecung.dddsample.businessdomain.usecases.authorsusecases.ALibrarianListsAllAuthors;
import fr.geromeavecung.dddsample.businessdomain.usecases.authorsusecases.ALibrarianReadsOneAuthor;
import fr.geromeavecung.dddsample.businessdomain.usecases.booksusecases.ALibrarianAddsABook;
import fr.geromeavecung.dddsample.businessdomain.usecases.booksusecases.ALibrarianListsAllBooks;
import fr.geromeavecung.dddsample.businessdomain.usecases.booksusecases.ALibrarianDisplaysABookDetails;
import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@Configuration
public class LibraryApplicationConfiguration {

    @Bean
    public ALibrarianDisplaysABookDetails aLibrarianDisplaysABookDetails(Books books, Authors authors) {
        return new ALibrarianDisplaysABookDetails(new ListBookDetails(books), new ReadOneAuthor(authors));
    }

    @Bean
    public ALibrarianListsAllBooks aLibrarianListsAllBooks(Books books, Authors authors) {
        // TODO bean of ReadAuthors ?
        return new ALibrarianListsAllBooks(new ListAllBooks(books), new ReadAuthors(authors));
    }

    @Bean
    public ALibrarianAddsABook aLibrarianAddsABook(Books books, Identifiers identifiers) {
        return new ALibrarianAddsABook(new AddABook(books), identifiers);
    }

    @Bean
    public ALibrarianReadsOneAuthor aLibrarianReadsOneAuthor(Authors authors) {
        return new ALibrarianReadsOneAuthor(new ReadOneAuthor(authors));
    }

    @Bean
    public ALibrarianListsAllAuthors aLibrarianListsAllAuthors(Authors authors) {
        return new ALibrarianListsAllAuthors(new ReadAuthors(authors));
    }

    @Bean
    public ALibrarianAddsAnAuthor aLibrarianAddsAnAuthor(Authors authors, Identifiers identifiers) {
        return new ALibrarianAddsAnAuthor(new AddAnAuthor(authors), identifiers);
    }

    @Bean
    public LayoutDialect layoutDialect() {
        return new LayoutDialect();
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource
                = new ReloadableResourceBundleMessageSource();

        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public CommonsRequestLoggingFilter logFilter() {
        CommonsRequestLoggingFilter filter
                = new CommonsRequestLoggingFilter();
        filter.setIncludeQueryString(true);
        filter.setIncludePayload(true);
        filter.setMaxPayloadLength(10000);
        filter.setIncludeHeaders(false);
        //filter.setAfterMessagePrefix("REQUEST DATA : ");
        return filter;
    }

}