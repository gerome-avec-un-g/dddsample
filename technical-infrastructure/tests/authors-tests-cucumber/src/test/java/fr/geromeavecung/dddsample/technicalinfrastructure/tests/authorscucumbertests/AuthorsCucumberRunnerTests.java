package fr.geromeavecung.dddsample.technicalinfrastructure.tests.authorscucumbertests;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        // TODO strict = true,
        plugin = {"json:target/authors-cucumber-tests.json"},
        glue = {"fr.geromeavecung.dddsample.technicalinfrastructure.tests.authorscucumbertests"},
        features = {"src/test/resources/features"},
        tags = "not @inProgress"
)
public class AuthorsCucumberRunnerTests {

}