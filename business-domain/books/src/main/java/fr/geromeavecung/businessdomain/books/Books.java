package fr.geromeavecung.businessdomain.books;

import java.util.Set;

public interface Books {

    Set<Book> findAll();

    //findOne ?

    void save(Book book);

}
