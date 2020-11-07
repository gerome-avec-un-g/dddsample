package fr.geromeavecung.businessdomain.books;

import fr.geromeavecung.businessdomain.shared.BusinessException;

public class BookAlreadyExists extends BusinessException {

    public BookAlreadyExists(Book book) {
        super(book.toString());
    }

}
