package fr.geromeavecung.dddsample.businessdomain.boundedcontexts.users;

import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core.FieldValidator;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core.Identifier;

public class User {

    private final Identifier identifier;

    public User(Identifier identifier) {
        this.identifier = FieldValidator.required("identifier", identifier);
    }

}
