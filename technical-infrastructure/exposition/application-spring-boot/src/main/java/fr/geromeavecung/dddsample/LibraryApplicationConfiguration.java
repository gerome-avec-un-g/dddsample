package fr.geromeavecung.dddsample;

import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core.Identifiers;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.books.Books;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.books.BooksService;
import fr.geromeavecung.dddsample.businessdomain.usecases.booksusecases.BooksPresentationService;
import nz.net.ultraq.thymeleaf.LayoutDialect;
import fr.geromeavecung.dddsample.businessdomain.usecases.booksusecases.BooksOrchestrationService;
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