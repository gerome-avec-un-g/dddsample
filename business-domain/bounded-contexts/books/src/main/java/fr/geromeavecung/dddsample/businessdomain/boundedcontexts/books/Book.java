package fr.geromeavecung.dddsample.businessdomain.boundedcontexts.books;

import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core.validation.FieldValidator;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core.Identifier;

public class Book {

    public enum Type {
        FICTION, TECHNOLOGY
    }

    private final Identifier identifier;

    private final Title title;

    private final Author author;

    private final Type type;

    public static Book create(Identifier identifier, Title title, Author author, Type type) {
        return new Book(identifier, title, author, type);
    }

    public Book(Identifier identifier, Title title, Author author, Type type) {
        this.identifier = FieldValidator.required("identifier", identifier);
        this.title = FieldValidator.required("title", title);
        this.author = FieldValidator.required("author", author);
        this.type = FieldValidator.required("type", type);
    }

    public Identifier getIdentifier() {
        return identifier;
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

}
