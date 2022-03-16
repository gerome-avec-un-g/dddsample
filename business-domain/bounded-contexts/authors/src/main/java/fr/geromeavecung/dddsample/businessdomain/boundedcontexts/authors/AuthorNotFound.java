package fr.geromeavecung.dddsample.businessdomain.boundedcontexts.authors;

import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core.BusinessException;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core.Identifier;

public class AuthorNotFound extends BusinessException {

    public AuthorNotFound(Identifier identifier) {
        super(identifier.toString());
    }

}
