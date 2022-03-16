package fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core.validation;

import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core.BusinessException;

public class FieldIsMandatory extends BusinessException {

    public FieldIsMandatory(String fieldName) {
        super(fieldName);
    }

}
