package fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core;

import java.util.Objects;

public class FirstName {

    private final String value;

    public static FirstName from(String value) {
        return new FirstName(value);
    }

    private FirstName(String value) {
        this.value = value;
    }

    public String display() {
        return value.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FirstName that = (FirstName) o;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
