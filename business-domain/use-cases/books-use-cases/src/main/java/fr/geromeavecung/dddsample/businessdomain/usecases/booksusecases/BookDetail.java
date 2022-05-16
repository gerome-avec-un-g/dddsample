package fr.geromeavecung.dddsample.businessdomain.usecases.booksusecases;

import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.authors.Author;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.books.Book;

public class BookDetail {

    private final Book book;

    private final Author author;

    public BookDetail(Book book, Author author) {
        this.book = book;
        this.author = author;
    }

    public String getIdentifier() {
        return book.getIdentifier().value().toString();
    }

    public String getTitle() {
        return book.getTitle().value();
    }

    public String getAuthorIdentifier() {
        return author.getIdentifier().value().toString();
    }

    public String getAuthor() {
        return author.getFirstName().value() + ' ' + author.getLastName().value();
    }

    public String getType() {
        return book.getType().name();
    }

}
