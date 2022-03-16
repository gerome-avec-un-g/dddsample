package fr.geromeavecung.dddsample.businessdomain.boundedcontexts.users;

import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core.validation.FieldValidator;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core.Identifier;

public class User {

    private final Identifier identifier;

    public User(Identifier identifier) {
        this.identifier = FieldValidator.required("identifier", identifier);
    }

    @Override
    public String toString() {
        return identifier.toString();
    }
}
