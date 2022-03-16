package fr.geromeavecung.dddsample.businessdomain.usecases.booksusecases;

import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.books.AddABook;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core.Identifiers;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.users.User;

public class ALibrarianAddsABook {

    private final AddABook addABook;

    private final Identifiers identifiers;

    public ALibrarianAddsABook(AddABook addABook, Identifiers identifiers) {
        this.addABook = addABook;
        this.identifiers = identifiers;
    }

    public void execute(User connectedUser, BookCreationForm authorCreationForm) {
        addABook.execute(authorCreationForm.toDomain(identifiers.generateNewIdentifier()));
    }

}
