package fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core;

import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core.validation.FormatValidator;

import java.util.Objects;

public class FirstName {

    private final String value;

    public static FirstName from(String value) {
        return new FirstName(value);
    }

    private FirstName(String value) {
        this.value = FormatValidator.validate("first name", value, "[a-zA-Z \\.-]{1,40}");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FirstName firstName = (FirstName) o;
        return value.equals(firstName.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value;
    }
}
