package fr.geromeavecung.dddsample.businessdomain.boundedcontexts.books;

import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core.BusinessException;

public class BookAlreadyExists extends BusinessException {

    public BookAlreadyExists(Book book) {
        super(book.getIdentifier().toString(), book.getTitle().getValue(), book.getAuthor().toString());
    }

}
