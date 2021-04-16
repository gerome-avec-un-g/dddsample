package fr.geromeavecung.exposition.presentation.cucumber.shared;

import fr.geromeavecung.exposition.presentation.cucumber.books.BooksConfiguration;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.test.context.ContextConfiguration;

@CucumberContextConfiguration
@ContextConfiguration(classes = {SharedState.class,
        TimestampsInMemory.class, IdentifiersInMemory.class,
        BooksConfiguration.class})
//@ComponentScan("fr.geromeavecung") does not seem to work
public class CucumberConfiguration {

}