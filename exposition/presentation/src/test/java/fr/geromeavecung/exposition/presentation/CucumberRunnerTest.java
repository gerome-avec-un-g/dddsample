package fr.geromeavecung.exposition.presentation;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        // TODO strict = true,
        plugin = {"json:target/cucumber.json"},
        glue = "fr.geromeavecung.exposition.presentation",
        features = {"src/test/resources/features"},
        tags = "not @inProgress"
        /*
         * run single feature: features = {"src/test/resources/features/add_books.feature"},
         * run specific tests to debug: tags = "@only"
         */
)
public class CucumberRunnerTest {

}