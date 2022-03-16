package fr.geromeavecung.dddsample.businessdomain.usecases.booksusecases;

import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.books.ListBookDetails;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core.Identifier;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.users.User;

public class ALibrarianDisplaysABookDetails {

    private final ListBookDetails listBookDetails;

    public ALibrarianDisplaysABookDetails(ListBookDetails listBookDetails) {
        this.listBookDetails = listBookDetails;
    }

    public BookDetail execute(User connectedUser, String uuidRepresentation) {
        return new BookDetail(listBookDetails.execute(Identifier.from(uuidRepresentation)));
    }
}
