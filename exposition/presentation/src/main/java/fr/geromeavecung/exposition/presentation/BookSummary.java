package fr.geromeavecung.exposition.presentation;

import fr.geromeavecung.businessdomain.books.Book;

import java.util.Objects;

public class BookSummary {

    private final String title;

    private final String author;

    private final String type;

    public BookSummary(String title, String author, String type) {
        this.title = title;
        this.author = author;
        this.type = type;
    }

    public BookSummary(Book book) {
        this.title = book.getTitle().getValue();
        this.author = book.getAuthor().getValue();
        this.type = book.getType().name();
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookSummary that = (BookSummary) o;
        return Objects.equals(title, that.title)
                && Objects.equals(author, that.author)
                && Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, type);
    }

    @Override
    public String toString() {
        return "BookSummary{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
