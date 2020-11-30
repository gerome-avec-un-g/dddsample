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
         * to just run one feature: features = {"src/test/resources/features/add_books.feature"},
         * to just run a few tests in debug: tags = "@only"
         */
)
public class CucumberRunnerTest {

}