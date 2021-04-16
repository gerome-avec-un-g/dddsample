package fr.geromeavecung.exposition.presentation.cucumber.books;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        // TODO strict = true,
        plugin = {"json:target/books.json"},
        glue = {"fr.geromeavecung.exposition.presentation.cucumber.shared", "fr.geromeavecung.exposition.presentation.cucumber.books"},
        features = {"src/test/resources/features"},
        tags = "not @inProgress"
)
public class BooksRunnerTest {

}