package fr.geromeavecung.dddsample.businessdomain.boundedcontexts.authors;

import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core.Identifier;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core.NotFound;

public class ReadOneAuthor {

    private final Authors authors;

    public ReadOneAuthor(Authors authors) {
        this.authors = authors;
    }

    public Author execute(Identifier identifier) {
        return authors.readAll().stream().filter(author -> author.getIdentifier().equals(identifier)).findFirst().orElseThrow(() -> new NotFound("author", identifier));
    }

}
