package fr.geromeavecung.exposition.presentation.cucumber.shared;

import fr.geromeavecung.exposition.presentation.cucumber.books.BooksConfiguration;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.test.context.ContextConfiguration;

@CucumberContextConfiguration
@ContextConfiguration(classes = {SharedConfiguration.class, BooksConfiguration.class})
public class CucumberConfiguration {

}