package fr.geromeavecung.dddsample.books;

import fr.geromeavecung.businessdomain.books.BooksService;
import fr.geromeavecung.dddsample.infrastructure.persistenceinmemory.BooksInMemory;
import fr.geromeavecung.exposition.orchestration.BooksOrchestrationService;
import fr.geromeavecung.exposition.presentation.BooksPresentationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HelloWorldConfig {

   @Bean
   public BooksPresentationService booksPresentationService(){
      return new BooksPresentationService(new BooksOrchestrationService(new BooksService(new BooksInMemory())));
   }

}