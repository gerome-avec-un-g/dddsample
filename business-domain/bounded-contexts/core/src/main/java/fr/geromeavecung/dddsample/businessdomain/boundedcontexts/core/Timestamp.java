package fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core;

import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core.validation.MandatoryValidator;

import java.time.ZonedDateTime;

public record Timestamp(ZonedDateTime value) {

    public Timestamp(ZonedDateTime value) {
        this.value = MandatoryValidator.validate("timestamp", value);
    }

}
