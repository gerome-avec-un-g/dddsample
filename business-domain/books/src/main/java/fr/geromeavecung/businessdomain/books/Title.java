package fr.geromeavecung.businessdomain.books;

import fr.geromeavecung.businessdomain.shared.FieldValidator;

public class Title {

    private final String value;

    public static Title create(String value) {
        return new Title(value);
    }

    public Title(String value) {
        this.value = FieldValidator.length("title", value, 1, 20);
    }

}
