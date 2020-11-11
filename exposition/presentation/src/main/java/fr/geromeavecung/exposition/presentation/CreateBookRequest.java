package fr.geromeavecung.exposition.presentation;

public class CreateBookRequest {

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
    public String toString() {
        return "CreateBookRequest{" + "title='" + title + '\'' + ", author='" + author + '\'' + '}';
    }

}
