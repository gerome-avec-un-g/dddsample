package fr.geromeavecung.dddsample;

import fr.geromeavecung.businessdomain.books.Books;
import fr.geromeavecung.businessdomain.books.BooksService;
import fr.geromeavecung.exposition.orchestration.BooksOrchestrationService;
import fr.geromeavecung.exposition.presentation.BooksPresentationService;
import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LibraryApplicationConfiguration {

   @Bean
   public BooksPresentationService booksPresentationService(Books books){
      return new BooksPresentationService(new BooksOrchestrationService(new BooksService(books)));
   }

   @Bean
   public LayoutDialect layoutDialect() {
      return new LayoutDialect();
   }

}