package fr.geromeavecung.dddsample.businessdomain.usecases.booksusecases;

import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core.Identifier;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.books.Book;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.books.Title;

public class BookCreationForm {

    // FIXME replace DTO by records
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

    public Book toDomain(Identifier identifier) {
        Title title = new Title(this.title);
        Identifier author = Identifier.from(this.author);
        return Book.create(identifier, title, author, type);
    }
}
