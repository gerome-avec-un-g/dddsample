package fr.geromeavecung.exposition.presentation.cucumber.books;

import fr.geromeavecung.exposition.presentation.cucumber.books.repositories.BooksInMemory;
import io.cucumber.spring.CucumberTestContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class BooksConfiguration {

    @Bean
    @Scope(CucumberTestContext.SCOPE_CUCUMBER_GLUE)
    BooksInMemory booksInMemory() {
        return new BooksInMemory();
    }

}
