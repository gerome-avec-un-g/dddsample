package fr.geromeavecung.dddsample.businessdomain.usecases.authorsusecases;

import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.authors.AddAnAuthor;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.authors.Author;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core.Identifiers;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.users.User;

public class ALibrarianAddsAnAuthor {

    private final AddAnAuthor addAnAuthor;

    private final Identifiers identifiers;

    public ALibrarianAddsAnAuthor(AddAnAuthor addAnAuthor, Identifiers identifiers) {
        this.addAnAuthor = addAnAuthor;
        this.identifiers = identifiers;
    }

    public void execute(User connectedUser, AuthorCreationForm authorCreationForm) {
        Author author = authorCreationForm.toDomain(identifiers.generateNewIdentifier());
        addAnAuthor.execute(author);
    }

}
