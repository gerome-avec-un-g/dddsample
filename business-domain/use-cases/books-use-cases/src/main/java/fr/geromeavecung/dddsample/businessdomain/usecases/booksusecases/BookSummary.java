package fr.geromeavecung.dddsample.businessdomain.usecases.booksusecases;

import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.authors.Author;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.books.Book;

public class BookSummary {

    private final Book book;

    private final Author author;

    public BookSummary(Book book, Author author) {
        this.book = book;
        this.author = author;
    }

    public String getIdentifier() {
        return book.getIdentifier().value().toString();
    }

    public String getTitle() {
        return book.getTitle().value();
    }

    public String getAuthorFirstName() {
        return author.getFirstName().value();
    }

    public String getAuthorLastName() {
        return author.getLastName().value();
    }

    public String getType() {
        return book.getType().name();
    }

}
