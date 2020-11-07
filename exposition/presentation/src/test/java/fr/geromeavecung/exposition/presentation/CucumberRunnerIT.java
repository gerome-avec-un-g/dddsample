package fr.geromeavecung.exposition.presentation;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        //strict = true,
        plugin = {"json:target/cucumber.json"},
        features = {"src/test/resources/features"},
        // to just run one feature features = {"src/test/resources/features/add_books.feature"},
        tags = "not @inProgress"
        // to just run a few tests in debug tags = "@only"
)
public class CucumberRunnerIT {

}