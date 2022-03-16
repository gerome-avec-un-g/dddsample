package fr.geromeavecung.dddsample.businessdomain.usecases.booksusecases;

import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.authors.Author;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.authors.ReadOneAuthor;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.books.Book;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.books.ListBookDetails;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core.Identifier;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.users.User;

public class ALibrarianDisplaysABookDetails {

    private final ListBookDetails listBookDetails;

    private final ReadOneAuthor readOneAuthor;

    public ALibrarianDisplaysABookDetails(ListBookDetails listBookDetails, ReadOneAuthor readOneAuthor) {
        this.listBookDetails = listBookDetails;
        this.readOneAuthor = readOneAuthor;
    }

    public BookDetail execute(User connectedUser, String uuidRepresentation) {
        Book book = listBookDetails.execute(Identifier.from(uuidRepresentation));
        Author author = readOneAuthor.execute(book.getAuthor());
        return new BookDetail(book, author);
    }

}
