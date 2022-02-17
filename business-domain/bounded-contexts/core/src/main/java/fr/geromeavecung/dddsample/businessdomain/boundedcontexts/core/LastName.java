package fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core;

import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core.validation.FieldValidator;

public class LastName {

    private final String value;

    public static LastName from(String value) {
        return new LastName(value);
    }

    private LastName(String value) {
        this.value = FieldValidator.required("last name", value);
    }

    public String display() {
        return value.toString();
    }

}
