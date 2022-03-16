package fr.geromeavecung.dddsample.businessdomain.usecases.authorsusecases;

import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.authors.ReadAuthors;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.users.User;

public class ALibrarianListsAllAuthors {

    private final ReadAuthors readAuthors;

    public ALibrarianListsAllAuthors(ReadAuthors readAuthors) {
        this.readAuthors = readAuthors;
    }

    public AuthorsListPage execute(User connectedUser) {
        return new AuthorsListPage(readAuthors.execute());
    }

}
