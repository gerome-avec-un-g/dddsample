package fr.geromeavecung.dddsample.businessdomain.boundedcontexts.books;

import fr.geromeavecung.businessdomain.shared.BusinessException;

import java.util.Arrays;
import java.util.List;

public class BookAlreadyExists extends BusinessException {

    private final Book book;

    public BookAlreadyExists(Book book) {
        super(book);
        this.book = book;
    }

    @Override
    public List<String> getParameters() {
        return Arrays.asList(book.getTitle().getValue(), book.getAuthor().getValue());
    }
}
