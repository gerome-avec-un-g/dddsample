package fr.geromeavecung.dddsample.technicalinfrastructure.tests.bookscucumbertests.books;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        // TODO strict = true,
        plugin = {"json:target/books-cucumber-tests.json"},
        glue = {"fr.geromeavecung.dddsample.technicalinfrastructure.tests.bookscucumbertests.shared", "fr.geromeavecung.dddsample.technicalinfrastructure.tests.bookscucumbertests.books"},
        features = {"src/test/resources/features"},
        tags = "not @inProgress"
)
public class BooksCucumberTestsRunner {

}