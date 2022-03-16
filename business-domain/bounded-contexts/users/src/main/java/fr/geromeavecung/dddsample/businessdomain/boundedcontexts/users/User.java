package fr.geromeavecung.dddsample.businessdomain.boundedcontexts.users;

import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core.validation.MandatoryValidator;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core.Identifier;

public class User {

    private final Identifier identifier;

    public User(Identifier identifier) {
        this.identifier = MandatoryValidator.validate("identifier", identifier);
    }

    @Override
    public String toString() {
        return identifier.toString();
    }
}
