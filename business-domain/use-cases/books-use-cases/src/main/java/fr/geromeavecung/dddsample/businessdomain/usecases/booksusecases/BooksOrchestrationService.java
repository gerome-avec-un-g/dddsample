package fr.geromeavecung.dddsample.businessdomain.usecases.booksusecases;

import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.books.Book;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.books.ListBookDetails;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.books.Title;

public class BooksOrchestrationService {

    private final ListBookDetails listBookDetails;

    public BooksOrchestrationService(ListBookDetails listBookDetails) {
        this.listBookDetails = listBookDetails;
    }

    public Book bookDetail(Title title) {
        return listBookDetails.bookDetail(title);
    }
}
