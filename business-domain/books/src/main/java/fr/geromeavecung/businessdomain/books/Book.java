package fr.geromeavecung.businessdomain.books;

import fr.geromeavecung.businessdomain.shared.FieldValidator;

import java.util.Objects;

public class Book {

    private final Title title;

    private final Author author;

    // publishing year, edition...

    public static Book create(Title title, Author author) {
        return new Book(title, author);
    }

    public Book(Title title, Author author) {
        this.title = FieldValidator.required("title", title);
        this.author = FieldValidator.required("author", author);
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
        return "Book{" + "title=" + title + ", author=" + author + '}';
    }

}
