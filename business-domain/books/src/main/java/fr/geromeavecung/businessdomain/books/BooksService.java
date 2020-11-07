package fr.geromeavecung.businessdomain.books;

public class BooksService {

    private final Books books;

    public BooksService(Books books) {
        this.books = books;
    }

    public void add(Book book) {
        books.add(book);
    }
}
