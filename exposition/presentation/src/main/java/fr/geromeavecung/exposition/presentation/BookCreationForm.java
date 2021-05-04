package fr.geromeavecung.exposition.presentation;

import fr.geromeavecung.businessdomain.books.Author;
import fr.geromeavecung.businessdomain.books.Book;
import fr.geromeavecung.businessdomain.books.Title;

import java.util.List;
import java.util.Objects;

public class BookCreationForm {

    private String title;

    private String author;

    private Book.Type type;

    private List<String> editions;

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

    public List<String> getEditions() {
        return editions;
    }

    public void setEditions(List<String> editions) {
        this.editions = editions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookCreationForm that = (BookCreationForm) o;
        return Objects.equals(title, that.title) && Objects.equals(author, that.author) && type == that.type && Objects.equals(editions, that.editions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, type, editions);
    }

    @Override
    public String toString() {
        return "BookCreationForm{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", type=" + type +
                ", editions=" + editions +
                '}';
    }

    public Book toDomain() {
        Title title = Title.create(this.title);
        Author author = Author.create(this.author);
        return Book.create(title, author, type);
    }
}
