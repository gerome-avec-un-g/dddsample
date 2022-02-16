package fr.geromeavecung.dddsample.businessdomain.boundedcontexts.books;

import fr.geromeavecung.businessdomain.shared.BusinessException;

public class BookAlreadyExists extends BusinessException {

    public BookAlreadyExists(Book book) {
        super(book.getTitle().getValue(), book.getAuthor().getValue());
    }

}
