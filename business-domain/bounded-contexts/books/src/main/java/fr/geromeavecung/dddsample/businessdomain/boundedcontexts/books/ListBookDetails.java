package fr.geromeavecung.dddsample.businessdomain.boundedcontexts.books;

import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core.Identifier;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core.NotFound;

import java.util.Optional;

public class ListBookDetails {

    private final Books books;

    public ListBookDetails(Books books) {
        this.books = books;
    }

    public Book execute(Identifier identifier) {
        Optional<Book> expectedBook = books.readAll().stream()
                .filter(book -> book.getIdentifier().equals(identifier)).findFirst();
        if (expectedBook.isPresent()) {
            return expectedBook.get();
        }
        throw new NotFound("book", identifier);
    }

}
