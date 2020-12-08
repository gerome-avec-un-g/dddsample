package fr.geromeavecung.businessdomain.books;

import fr.geromeavecung.businessdomain.shared.FieldValidator;

import java.util.Objects;

public class Book {

    public enum Type {
        FICTION, TECHNOLOGY
    }

    private final Title title;

    private final Author author;

    private final Type type;

    public static Book create(Title title, Author author, Type type) {
        return new Book(title, author, type);
    }

    public Book(Title title, Author author, Type type) {
        this.title = FieldValidator.required("title", title);
        this.author = FieldValidator.required("author", author);
        this.type = FieldValidator.required("type", type);
    }

    public Title getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }

    public Type getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return title.equals(book.title) && author.equals(book.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author);
    }

    @Override
    public String toString() {
        return "Book{" +
                "title=" + title +
                ", author=" + author +
                ", type=" + type +
                '}';
    }

}
