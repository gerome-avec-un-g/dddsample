package fr.geromeavecung.dddsample.businessdomain.usecases.booksusecases;

import java.util.List;

public class BookSummaryTable {

    // FIXME list vs set ? unicity is ensured by repo ? here we want sorting order
    private final List<BookSummary> bookSummaries;

    public BookSummaryTable(List<BookSummary> bookSummaries) {
        this.bookSummaries = bookSummaries;
    }

    public List<BookSummary> getBookSummaries() {
        return bookSummaries;
    }

}
