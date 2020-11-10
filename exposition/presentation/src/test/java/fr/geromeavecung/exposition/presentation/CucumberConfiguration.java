package fr.geromeavecung.exposition.presentation;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.test.context.ContextConfiguration;

@CucumberContextConfiguration
@ContextConfiguration(classes = {BooksInMemory.class, CucumberState.class})
//@ComponentScan("fr.geromeavecung.exposition.presentation")
public class CucumberConfiguration {

}