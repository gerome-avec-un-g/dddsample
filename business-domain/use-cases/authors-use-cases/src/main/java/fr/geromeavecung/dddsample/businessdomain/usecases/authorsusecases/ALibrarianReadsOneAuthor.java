package fr.geromeavecung.dddsample.businessdomain.usecases.authorsusecases;

import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.authors.ReadOneAuthor;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core.Identifier;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.users.User;

public class ALibrarianReadsOneAuthor {

    private final ReadOneAuthor readOneAuthor;

    public ALibrarianReadsOneAuthor(ReadOneAuthor readOneAuthor) {
        this.readOneAuthor = readOneAuthor;
    }

    public AuthorPage execute(User connectedUser, String uuidRepresentation) {
        return new AuthorPage(readOneAuthor.execute(Identifier.from(uuidRepresentation)));
    }

}
