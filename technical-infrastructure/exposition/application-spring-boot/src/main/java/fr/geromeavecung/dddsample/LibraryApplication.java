package fr.geromeavecung.dddsample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * @SpringBootApplication is an alias for 3 more annotations
 */
@SpringBootApplication
public class LibraryApplication {

    /**
     * If issues when launching, check if IntelliJ run configuration is using bundled java
     *
     * If issues when debugging launch by right-click on this class, not by maven. Check the end of
     * https://stackoverflow.com/questions/44096708/how-to-debug-spring-boot-application-with-intellij-idea-community-edition
     */
    public static void main(String[] args) {
        SpringApplication.run(LibraryApplication.class, args);
    }

}