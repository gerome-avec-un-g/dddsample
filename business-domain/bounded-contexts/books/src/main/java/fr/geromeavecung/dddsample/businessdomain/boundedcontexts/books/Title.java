package fr.geromeavecung.dddsample.businessdomain.boundedcontexts.books;

import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core.validation.LengthValidator;

public record Title(String value) {

    public Title(String value) {
        this.value = LengthValidator.validate("title", value, 1, 40);
    }

}
