package fr.geromeavecung.dddsample.businessdomain.boundedcontexts.books;

import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core.validation.FieldValidator;

import java.util.Objects;

@Deprecated
public class Author implements Comparable<Author>{

    private final String value;

    public static Author create(String value) {
        return new Author(value);
    }

    public Author(String value) {
        this.value = FieldValidator.length("author", value, 1, 25);
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
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

    @Override
    public int compareTo(Author o) {
        return value.compareTo(o.value);
    }

}
