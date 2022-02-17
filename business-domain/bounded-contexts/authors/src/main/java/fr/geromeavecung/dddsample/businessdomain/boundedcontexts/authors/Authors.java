package fr.geromeavecung.dddsample.businessdomain.boundedcontexts.authors;

import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core.Identifier;

import java.util.Optional;
import java.util.Set;

public interface Authors {

    Set<Author> readAll();

    Optional<Author> read(Identifier identifier);

    void save(Author author);

}
