package fr.geromeavecung.exposition.presentation;

import fr.geromeavecung.businessdomain.books.Book;

import java.util.Objects;

public class BookSummary {

    private final String title;

    private final String author;

    public BookSummary(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public BookSummary(Book book) {
        this.title = book.getTitle().getValue();
        this.author = book.getAuthor().getValue();
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookSummary that = (BookSummary) o;
        return Objects.equals(title, that.title) && Objects.equals(author, that.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author);
    }

    @Override
    public String toString() {
        return "BookSummary{" + "title='" + title + '\'' + ", author='" + author + '\'' + '}';
    }

}
