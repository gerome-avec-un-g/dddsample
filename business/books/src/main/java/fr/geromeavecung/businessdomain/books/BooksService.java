package fr.geromeavecung.businessdomain.books;

import fr.geromeavecung.businessdomain.shared.NotFoundException;

import java.util.Optional;
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

    public Book bookDetail(Title title) {
        Optional<Book> expectedBook = books.findAll().stream()
                .filter(book -> book.getTitle().equals(title)).findFirst();
        if (expectedBook.isPresent()) {
            return expectedBook.get();
        }
        throw new NotFoundException("book", title.getValue());
    }
}
