package fr.geromeavecung.dddsample.technicalinfrastructure.tests.bookscucumbertests.books.repositories;

import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core.Identifier;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.books.Book;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.books.Books;
import io.cucumber.spring.CucumberTestContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Scope(CucumberTestContext.SCOPE_CUCUMBER_GLUE)
public class BooksForCucumber implements Books {

    private final Set<Book> books = new HashSet<>();

    @Override
    public Set<Book> readAll() {
        return books;
    }

    @Override
    public Optional<Book> read(Identifier identifier) {
        return books.stream().filter(book -> book.getIdentifier().equals(identifier)).findFirst();
    }

    @Override
    public void save(Book book) {
        books.add(book);
    }

}
