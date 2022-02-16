package fr.geromeavecung.exposition.orchestration;

import fr.geromeavecung.businessdomain.books.Book;
import fr.geromeavecung.businessdomain.books.BooksService;
import fr.geromeavecung.businessdomain.books.Title;

import java.util.Set;

public class BooksOrchestrationService {

    private final BooksService booksService;

    public BooksOrchestrationService(BooksService booksService) {
        this.booksService = booksService;
    }

    public void add(Book book) {
        // TODO add authorization
        booksService.add(book);
    }

    public Set<Book> displayBooks() {
        return booksService.displayBooks();
    }

    public Book bookDetail(Title title) {
        return booksService.bookDetail(title);
    }
}
