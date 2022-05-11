package fr.geromeavecung.dddsample.businessdomain.boundedcontexts.authors;

import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core.BusinessException;

public class AuthorAlreadyExists extends BusinessException {

    public AuthorAlreadyExists(Author existingAuthor) {
        super(existingAuthor.getIdentifier().toString(), existingAuthor.getFirstName().value(), existingAuthor.getLastName().value());
    }

}
