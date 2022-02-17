package fr.geromeavecung.dddsample.technicalinfrastructure.tests.authorscucumbertests;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.test.context.ContextConfiguration;

@CucumberContextConfiguration
@ContextConfiguration(classes = {AuthorsCucumberTestsConfiguration.class})
public class CucumberMainConfiguration {

}