package fr.geromeavecung.dddsample.businessdomain.usecases.booksusecases;

import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.books.Book;

import java.util.Objects;

public class BookDetail {

    private final String title;

    private final String author;// FIXME

    private final String type;

    public BookDetail(String title, String author, String type) {
        this.title = title;
        this.author = author;
        this.type = type;
    }

    public BookDetail(Book book) {
        this.title = book.getTitle().getValue();
        this.author = book.getAuthor().toString();
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
        BookDetail that = (BookDetail) o;
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
