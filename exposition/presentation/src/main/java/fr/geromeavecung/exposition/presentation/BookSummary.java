package fr.geromeavecung.exposition.presentation;

import fr.geromeavecung.businessdomain.books.Book;

import java.util.Objects;

public class BookSummary {

    private final Book book;

    public BookSummary(Book book) {
        this.book = book;
    }

    public String getTitle() {
        return book.getTitle().getValue();
    }

    public String getAuthor() {
        return book.getAuthor().getValue();
    }

    public String getType() {
        return book.getType().name();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookSummary that = (BookSummary) o;
        return Objects.equals(book, that.book);
    }

    @Override
    public int hashCode() {
        return Objects.hash(book);
    }

    @Override
    public String toString() {
        return "BookSummary{" +
                "book='" + book + '\'' +
                '}';
    }
}
