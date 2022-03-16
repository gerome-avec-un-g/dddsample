package fr.geromeavecung.dddsample.technicalinfrastructure.tests.bookscucumbertests.books.repositories;

import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.authors.Author;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.authors.Authors;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core.Identifier;
import io.cucumber.spring.CucumberTestContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Scope(CucumberTestContext.SCOPE_CUCUMBER_GLUE)
public class AuthorsForCucumber implements Authors {

    private final Set<Author> authors = new HashSet<>();

    @Override
    public Set<Author> readAll() {
        return authors;
    }

    @Override
    public Optional<Author> read(Identifier identifier) {
        return authors.stream().filter(book -> book.getIdentifier().equals(identifier)).findFirst();
    }

    @Override
    public void save(Author author) {
        authors.add(author);
    }

}
