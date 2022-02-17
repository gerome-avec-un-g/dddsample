package fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core;

public class FieldRequired extends BusinessException {

    public FieldRequired(String fieldName) {
        super(fieldName);
    }

}
