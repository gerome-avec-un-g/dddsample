package fr.geromeavecung.dddsample;

import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.authors.AddAnAuthor;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.authors.Authors;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.authors.ReadAuthors;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core.Identifiers;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.books.Books;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.books.BooksService;
import fr.geromeavecung.dddsample.businessdomain.usecases.authorsusecases.ALibrarianAddsAnAuthor;
import fr.geromeavecung.dddsample.businessdomain.usecases.authorsusecases.ALibrarianListsAllAuthors;
import fr.geromeavecung.dddsample.businessdomain.usecases.booksusecases.BooksPresentationService;
import fr.geromeavecung.dddsample.businessdomain.usecases.booksusecases.BooksOrchestrationService;
import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@Configuration
public class LibraryApplicationConfiguration {

    @Bean
    public BooksPresentationService booksPresentationService(Books books, Identifiers identifiers) {
        return new BooksPresentationService(new BooksOrchestrationService(new BooksService(books)), identifiers);
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