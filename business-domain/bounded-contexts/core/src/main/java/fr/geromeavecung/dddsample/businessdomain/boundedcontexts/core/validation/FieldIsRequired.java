package fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core.validation;

import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core.BusinessException;

public class FieldIsRequired extends BusinessException {

    public FieldIsRequired(String fieldName) {
        super(fieldName);
    }

}
