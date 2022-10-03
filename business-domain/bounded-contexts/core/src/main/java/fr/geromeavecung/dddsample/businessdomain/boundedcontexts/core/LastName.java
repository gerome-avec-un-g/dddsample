package fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core;

import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core.validation.MandatoryValidator;

public record LastName(String value) {

    public LastName(String value) {
        this.value = MandatoryValidator.validate("last name", value);
    }

    @Override
    public String toString() {
        return value;
    }
}
