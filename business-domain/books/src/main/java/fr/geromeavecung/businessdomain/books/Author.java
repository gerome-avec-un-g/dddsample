package fr.geromeavecung.businessdomain.books;

import fr.geromeavecung.businessdomain.shared.FieldValidator;

public class Author {

    private final String value;

    public static Author create(String value) {
        return new Author(value);
    }

    public Author(String value) {
        this.value = FieldValidator.length("author", value, 1, 15);
    }
}
