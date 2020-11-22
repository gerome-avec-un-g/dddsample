package fr.geromeavecung.businessdomain.books;

import java.util.Set;

public class BooksService {

    private final Books books;

    public BooksService(Books books) {
        this.books = books;
    }

    public void add(Book book) {
        if (books.findAll().contains(book)) {
            throw new BookAlreadyExists(book);
        }
        books.save(book);
    }

    public Set<Book> displayBooks() {
        return books.findAll();
    }
}
