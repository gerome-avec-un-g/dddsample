package fr.geromeavecung.dddsample;

import fr.geromeavecung.businessdomain.books.BooksService;
import fr.geromeavecung.dddsample.infrastructure.persistenceinmemory.BooksInMemory;
import fr.geromeavecung.exposition.orchestration.BooksOrchestrationService;
import fr.geromeavecung.exposition.presentation.BooksPresentationService;
import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

   @Bean
   public BooksPresentationService booksPresentationService(){
      return new BooksPresentationService(new BooksOrchestrationService(new BooksService(new BooksInMemory())));
   }

   @Bean
   public LayoutDialect layoutDialect() {
      return new LayoutDialect();
   }

}