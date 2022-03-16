package fr.geromeavecung.dddsample.businessdomain.usecases.booksusecases;

import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.books.Book;

public class BookSummary {

    private final Book book;

    public BookSummary(Book book) {
        this.book = book;
    }

    public String getIdentifier() {
        return book.getIdentifier().display();
    }

    public String getTitle() {
        return book.getTitle().getValue();
    }

    public String getAuthor() {
        return book.getAuthor().getValue();
    }

    public String getType() {
        return book.getType().name();
    }

}
