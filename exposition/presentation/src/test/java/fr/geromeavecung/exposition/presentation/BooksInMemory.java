package fr.geromeavecung.exposition.presentation;

import fr.geromeavecung.businessdomain.books.Book;
import fr.geromeavecung.businessdomain.books.Books;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Scope("cucumber-glue") //@ScenarioScope  does not seem to work
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
