package fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core.validation;

import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core.BusinessException;

public class FieldIsNotProperlyFormatted extends BusinessException {

    public FieldIsNotProperlyFormatted(String fieldName, String fieldValue, String pattern) {
        super(fieldName, fieldValue, pattern);
    }

}
