package fr.geromeavecung.dddsample.businessdomain.boundedcontexts.users;

import fr.geromeavecung.businessdomain.shared.FieldValidator;
import fr.geromeavecung.businessdomain.shared.Identifier;

public class User {

    private final Identifier identifier;

    public User(Identifier identifier) {
        this.identifier = FieldValidator.required("identifier", identifier);
    }

}
