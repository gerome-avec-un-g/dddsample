package fr.geromeavecung.dddsample.businessdomain.usecases.authorsusecases;

import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.authors.Author;

public class AuthorPage {

    private final Author author;

    public AuthorPage(Author author) {
        this.author = author;
    }

    public String getFirstNameAndLastName() {
        return author.getFirstName().toString() + ' ' + author.getLastName().toString();
    }

}
