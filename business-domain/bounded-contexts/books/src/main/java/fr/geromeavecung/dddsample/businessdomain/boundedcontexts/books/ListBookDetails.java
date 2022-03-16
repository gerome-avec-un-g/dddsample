package fr.geromeavecung.dddsample.businessdomain.boundedcontexts.books;

import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core.NotFoundException;

import java.util.Optional;

public class ListBookDetails {

    private final Books books;

    public ListBookDetails(Books books) {
        this.books = books;
    }

    public Book bookDetail(Title title) {
        Optional<Book> expectedBook = books.readAll().stream()
                .filter(book -> book.getTitle().equals(title)).findFirst();
        if (expectedBook.isPresent()) {
            return expectedBook.get();
        }
        throw new NotFoundException("book", title.getValue());
    }

}
