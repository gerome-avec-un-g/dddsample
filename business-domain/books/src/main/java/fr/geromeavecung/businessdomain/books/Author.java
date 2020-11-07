package fr.geromeavecung.businessdomain.books;

import fr.geromeavecung.businessdomain.shared.FieldValidator;

import java.util.Objects;

public class Author {

    private final String value;

    public static Author create(String value) {
        return new Author(value);
    }

    public Author(String value) {
        this.value = FieldValidator.length("author", value, 1, 15);
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return Objects.equals(value, author.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Author{" + "value='" + value + '\'' + '}';
    }

}
