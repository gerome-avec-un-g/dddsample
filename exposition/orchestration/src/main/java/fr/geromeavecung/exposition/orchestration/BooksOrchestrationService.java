package fr.geromeavecung.exposition.orchestration;

import fr.geromeavecung.businessdomain.books.BooksService;

public class BooksOrchestrationService {

    private final BooksService booksService;

    public BooksOrchestrationService(BooksService booksService) {
        this.booksService = booksService;
    }

}
