package fr.geromeavecung.exposition.presentation;

import fr.geromeavecung.businessdomain.books.Book;

import java.util.Objects;

public class BookCreationForm {

    private String title;

    private String author;

    private Book.Type type;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Book.Type getType() {
        return type;
    }

    public void setType(Book.Type type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookCreationForm that = (BookCreationForm) o;
        return Objects.equals(title, that.title) &&
                Objects.equals(author, that.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author);
    }

    @Override
    public String toString() {
        return "CreateBookForm{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", type=" + type +
                '}';
    }
}
