package fr.geromeavecung.exposition.presentation;

import java.util.Objects;

public class CreateBookForm {

    // default constructor + setters for thymeleaf

    private String title;

    private String author;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateBookForm that = (CreateBookForm) o;
        return Objects.equals(title, that.title) &&
                Objects.equals(author, that.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author);
    }

    @Override
    public String toString() {
        return "CreateBookForm{" + "title='" + title + '\'' + ", author='" + author + '\'' + '}';
    }

}
