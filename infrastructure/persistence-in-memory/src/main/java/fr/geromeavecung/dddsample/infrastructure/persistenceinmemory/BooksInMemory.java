package fr.geromeavecung.dddsample.infrastructure.persistenceinmemory;

import fr.geromeavecung.businessdomain.books.Author;
import fr.geromeavecung.businessdomain.books.Book;
import fr.geromeavecung.businessdomain.books.Books;
import fr.geromeavecung.businessdomain.books.Title;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Repository;

@Repository
public class BooksInMemory implements Books {

    // TODO rename package for "production" database
    // TODO move this class in cucumber package

    private final Set<Book> books = new HashSet<>();

    public BooksInMemory() {
        books.add(Book.create(Title.create("Red Mars"), Author.create("Kim Stanley Robinson"), Book.Type.FICTION));
    }

    @Override
    public Set<Book> findAll() {
        return books;
    }

    @Override
    public void save(Book book) {
        books.add(book);
    }

}