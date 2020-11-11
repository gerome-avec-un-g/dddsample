package fr.geromeavecung.businessdomain.books;

import java.util.Set;

public interface Books {

    Set<Book> all();

    void add(Book book);

}
