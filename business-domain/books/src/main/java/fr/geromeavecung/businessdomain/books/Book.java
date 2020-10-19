package fr.geromeavecung.businessdomain.books;

import fr.geromeavecung.businessdomain.shared.FieldValidator;

public class Book {

    private final String title;

    private Book(String title) {
        this.title = FieldValidator.maximumLength("title", title, 20);
    }

    public static Book create(String title) {
        return new Book(title);
    }
}
