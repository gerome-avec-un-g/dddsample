package fr.geromeavecung.dddsample.businessdomain.boundedcontexts.books;

import java.util.Optional;

public class AddABook {

    private final Books books;

    public AddABook(Books books) {
        this.books = books;
    }

    public void execute(Book bookToBeAdded) {
        if (books.readAll().stream()
                .anyMatch(existingBook -> existingBook.getTitle().equals(bookToBeAdded.getTitle()) &&
                        existingBook.getAuthor().equals(bookToBeAdded.getAuthor()))) {
            throw new BookAlreadyExists(bookToBeAdded);
        }
        books.save(bookToBeAdded);
    }

}
