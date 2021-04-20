package fr.geromeavecung.exposition.presentation.cucumber.books.repositories;

import fr.geromeavecung.businessdomain.books.Book;
import fr.geromeavecung.businessdomain.books.Books;
import io.cucumber.spring.CucumberTestContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Scope(CucumberTestContext.SCOPE_CUCUMBER_GLUE)
public class BooksInMemory implements Books {

    private final Set<Book> books = new HashSet<>();

    @Override
    public Set<Book> findAll() {
        return books;
    }

    @Override
    public void save(Book book) {
        books.add(book);
    }

}
