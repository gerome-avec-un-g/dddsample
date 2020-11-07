package fr.geromeavecung.dddsample.infrastructure.persistenceinmemory;

import fr.geromeavecung.businessdomain.books.Book;
import fr.geromeavecung.businessdomain.books.Books;

import java.util.HashSet;
import java.util.Set;

public class BooksInMemory implements Books {

    private final Set<Book> books = new HashSet<>();

    @Override
    public Set<Book> all() {
        return books;
    }

    @Override
    public void add(Book book) {
        books.add(book);
    }

}