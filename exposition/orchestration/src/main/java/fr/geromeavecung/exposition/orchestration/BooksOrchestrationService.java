package fr.geromeavecung.exposition.orchestration;

import fr.geromeavecung.businessdomain.books.Book;
import fr.geromeavecung.businessdomain.books.BooksService;

public class BooksOrchestrationService {

    private final BooksService booksService;

    public BooksOrchestrationService(BooksService booksService) {
        this.booksService = booksService;
    }

    public void add(Book book) {
        // TODO add authorization
        booksService.add(book);
    }

}
