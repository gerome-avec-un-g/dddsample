package fr.geromeavecung.dddsample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LibraryApplication {

    /**
     * If issues when launching, check if run configuration is using bundled java
     */

    public static void main(String[] args) {
        SpringApplication.run(LibraryApplication.class, args);
    }

}