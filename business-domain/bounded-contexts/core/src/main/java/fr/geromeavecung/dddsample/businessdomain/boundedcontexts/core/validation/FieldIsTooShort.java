package fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core.validation;

import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core.BusinessException;

public class FieldIsTooShort extends BusinessException {

    public FieldIsTooShort(String fieldName, String fieldValue, int minimumLength) {
        super(fieldName, fieldValue, Integer.toString(minimumLength));
    }

}
