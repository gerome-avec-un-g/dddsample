package fr.geromeavecung.dddsample.businessdomain.usecases.authorsusecases;

import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.authors.Author;

public class AuthorRow {

    private final Author author;

    public AuthorRow(Author author) {
        this.author = author;
    }

    public String getFirstName() {
        return author.getFirstName().toString();
    }

    public String getLastName() {
        return author.getLastName().toString();
    }

}
