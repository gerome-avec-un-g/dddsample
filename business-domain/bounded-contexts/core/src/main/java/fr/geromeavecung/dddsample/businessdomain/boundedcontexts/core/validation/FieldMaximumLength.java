package fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core.validation;

import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core.BusinessException;

public class FieldMaximumLength extends BusinessException {

    public FieldMaximumLength(String fieldName, String fieldValue, int maximumLength) {
        super(fieldName, fieldValue, Integer.toString(maximumLength));
    }

}
