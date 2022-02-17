package fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core.validation;

import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core.BusinessException;

public class FieldRequired extends BusinessException {

    public FieldRequired(String fieldName) {
        super(fieldName);
    }

}
