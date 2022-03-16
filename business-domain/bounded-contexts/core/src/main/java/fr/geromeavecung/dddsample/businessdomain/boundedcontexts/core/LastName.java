package fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core;

import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core.validation.FieldValidator;

import java.util.Objects;

public class LastName {

    private final String value;

    public static LastName from(String value) {
        return new LastName(value);
    }

    private LastName(String value) {
        this.value = FieldValidator.required("last name", value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LastName lastName = (LastName) o;
        return value.equals(lastName.value);
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
