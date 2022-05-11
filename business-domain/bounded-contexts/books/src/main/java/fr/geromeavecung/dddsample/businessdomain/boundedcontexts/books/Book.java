package fr.geromeavecung.dddsample.businessdomain.boundedcontexts.books;

import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core.validation.MandatoryValidator;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core.Identifier;

public class Book {

    public enum Type {
        FICTION, TECHNOLOGY
    }

    private final Identifier identifier;

    private final Title title;

    private final Identifier author;

    private final Type type;

    public static Book create(Identifier identifier, Title title, Identifier author, Type type) {
        return new Book(identifier, title, author, type);
    }

    public Book(Identifier identifier, Title title, Identifier author, Type type) {
        this.identifier = MandatoryValidator.validate("identifier", identifier);
        this.title = MandatoryValidator.validate("title", title);
        this.author = MandatoryValidator.validate("author", author);
        this.type = MandatoryValidator.validate("type", type);
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public Title getTitle() {
        return title;
    }

    public Identifier getAuthorIdentifier() {
        return author;
    }

    public Type getType() {
        return type;
    }

}
