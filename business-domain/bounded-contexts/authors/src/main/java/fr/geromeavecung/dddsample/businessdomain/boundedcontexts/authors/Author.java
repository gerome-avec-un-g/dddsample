package fr.geromeavecung.dddsample.businessdomain.boundedcontexts.authors;

import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core.FirstName;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core.Identifier;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core.LastName;

public class Author {

    private final Identifier identifier;

    private final FirstName firstName;

    private final LastName lastName;

    public static Author create(Identifier identifier, FirstName firstName, LastName lastName) {
        return new Author(identifier, firstName, lastName);
    }

    public static Author read(Identifier identifier, FirstName firstName, LastName lastName) {
        return new Author(identifier, firstName, lastName);
    }

    private Author(Identifier identifier, FirstName firstName, LastName lastName) {
        this.identifier = identifier;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public FirstName getFirstName() {
        return firstName;
    }

    public LastName getLastName() {
        return lastName;
    }

}
