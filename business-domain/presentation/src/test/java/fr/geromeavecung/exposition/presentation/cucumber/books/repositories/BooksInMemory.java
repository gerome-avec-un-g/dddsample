package fr.geromeavecung.exposition.presentation.cucumber.books.repositories;

import fr.geromeavecung.businessdomain.shared.Identifier;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.books.Book;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.books.Books;
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
    public Set<Book> readdAll() {
        return books;
    }

    @Override
    public Book read(Identifier identifier) {
        return null;
    }

    @Override
    public void save(Book book) {
        books.add(book);
    }

}
