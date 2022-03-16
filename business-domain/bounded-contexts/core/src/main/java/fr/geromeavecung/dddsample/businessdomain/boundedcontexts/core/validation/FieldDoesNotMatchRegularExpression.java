package fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core.validation;

import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core.BusinessException;

public class FieldDoesNotMatchRegularExpression extends BusinessException {

    public FieldDoesNotMatchRegularExpression(String fieldName, String fieldValue, String pattern) {
        super(fieldName, fieldValue, pattern);
    }

}
