package fr.geromeavecung.dddsample.businessdomain.boundedcontexts.books;

import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core.Identifier;

import java.util.Optional;
import java.util.Set;

public interface Books {

    Set<Book> readAll();

    Optional<Book> read(Identifier identifier);

    void save(Book book);

}
