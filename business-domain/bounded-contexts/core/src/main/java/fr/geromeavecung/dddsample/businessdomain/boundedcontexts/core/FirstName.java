package fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core;

import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core.validation.FormatValidator;

public record FirstName(String value) {

    public FirstName(String value) {
        this.value = FormatValidator.validate("first name", value, "[a-zA-Z \\.-]{1,40}");
    }

}
