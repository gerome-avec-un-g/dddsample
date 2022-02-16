package fr.geromeavecung.dddsample.businessdomain.boundedcontexts.books;

import fr.geromeavecung.businessdomain.shared.NotFoundException;

import java.util.Optional;
import java.util.Set;

public class BooksService {

    private final Books books;

    public BooksService(Books books) {
        this.books = books;
    }

    public void add(Book bookToBeAdded) {
        if (books.readAll().stream()
                .anyMatch(existingBook -> existingBook.getTitle().equals(bookToBeAdded.getTitle()) &&
                        existingBook.getAuthor().equals(bookToBeAdded.getAuthor()))) {
            throw new BookAlreadyExists(bookToBeAdded);
        }
        books.save(bookToBeAdded);
    }

    public Set<Book> displayBooks() {
        return books.readAll();
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
