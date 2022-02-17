package fr.geromeavecung.dddsample.businessdomain.boundedcontexts.books;

import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core.FieldValidator;

import java.util.Objects;

public class Title implements Comparable<Title> {

    private final String value;

    public static Title create(String value) {
        return new Title(value);
    }

    public Title(String value) {
        this.value = FieldValidator.length("title", value, 1, 40);
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
        Title title = (Title) o;
        return Objects.equals(value, title.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public int compareTo(Title o) {
        return value.compareTo(o.value);
    }

}
