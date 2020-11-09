package fr.geromeavecung.exposition.presentation;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;

@CucumberContextConfiguration
@ContextConfiguration(classes = {BooksInMemory.class, CucumberState.class})
//@ComponentScan("fr.geromeavecung.exposition.presentation")
public class CucumberSpringConfiguration {

//    @Configuration
//    //@ComponentScan("fr.geromeavecung.exposition.presentation")
//    //@ContextConfiguration(classes = BooksInMemory.class)
//    public static  class ConfigurationCucumber {
//
//    }

}