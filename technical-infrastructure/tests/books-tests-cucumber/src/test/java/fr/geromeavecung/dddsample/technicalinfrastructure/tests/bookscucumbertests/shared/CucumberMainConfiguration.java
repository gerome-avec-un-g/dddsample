package fr.geromeavecung.dddsample.technicalinfrastructure.tests.bookscucumbertests.shared;

import fr.geromeavecung.dddsample.technicalinfrastructure.tests.bookscucumbertests.books.BooksCucumberTestsConfiguration;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.test.context.ContextConfiguration;

@CucumberContextConfiguration
@ContextConfiguration(classes = {SharedConfiguration.class, BooksCucumberTestsConfiguration.class})
public class CucumberMainConfiguration {

}