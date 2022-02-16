package fr.geromeavecung.dddsample.businessdomain.boundedcontexts.books;

import fr.geromeavecung.businessdomain.shared.Identifier;

import java.util.Set;

public interface Books {

    Set<Book> readdAll();

    Book read(Identifier identifier);

    void save(Book book);

}
