package fr.geromeavecung.dddsample.businessdomain.boundedcontexts.books;

import java.util.Set;

public class ListAllBooks {

    private final Books books;

    public ListAllBooks(Books books) {
        this.books = books;
    }

    public Set<Book> execute() {
        return books.readAll();
    }

}
