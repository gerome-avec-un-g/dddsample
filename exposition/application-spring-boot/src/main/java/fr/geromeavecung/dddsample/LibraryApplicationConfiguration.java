package fr.geromeavecung.dddsample;

import fr.geromeavecung.businessdomain.books.Books;
import fr.geromeavecung.businessdomain.books.BooksService;
import fr.geromeavecung.exposition.orchestration.BooksOrchestrationService;
import fr.geromeavecung.exposition.presentation.BooksPresentationService;
import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@Configuration
public class LibraryApplicationConfiguration {

    @Bean
    public BooksPresentationService booksPresentationService(Books books) {
        return new BooksPresentationService(new BooksOrchestrationService(new BooksService(books)));
    }

    @Bean
    public LayoutDialect layoutDialect() {
        return new LayoutDialect();
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