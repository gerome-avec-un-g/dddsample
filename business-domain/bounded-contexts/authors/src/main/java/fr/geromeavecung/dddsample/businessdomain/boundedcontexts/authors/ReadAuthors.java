package fr.geromeavecung.dddsample.businessdomain.boundedcontexts.authors;

import java.util.Set;

public class ReadAuthors {

    private final Authors authors;

    public ReadAuthors(Authors authors) {
        this.authors = authors;
    }

    public Set<Author> execute() {
        return authors.readAll();
    }

}
