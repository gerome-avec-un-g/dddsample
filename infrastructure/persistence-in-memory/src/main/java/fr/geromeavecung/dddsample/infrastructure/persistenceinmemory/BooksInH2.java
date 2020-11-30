package fr.geromeavecung.dddsample.infrastructure.persistenceinmemory;

import fr.geromeavecung.businessdomain.books.Book;
import fr.geromeavecung.businessdomain.books.Books;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Repository
public class BooksInH2 implements Books {

    /*
     * with spring-dev-tools we can access H2 at http://localhost:8080/h2-console
     */

    private final BooksJpaRepository booksJpaRepository;

    @Autowired
    public BooksInH2(BooksJpaRepository booksJpaRepository) {
        this.booksJpaRepository = booksJpaRepository;
    }

    @Override
    public Set<Book> findAll() {
        return StreamSupport.stream(booksJpaRepository.findAll().spliterator(), false)
                .map(BookJPA::toDomain)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());
    }

    @Override
    public void save(Book book) {
        booksJpaRepository.save(new BookJPA(book));
    }

}