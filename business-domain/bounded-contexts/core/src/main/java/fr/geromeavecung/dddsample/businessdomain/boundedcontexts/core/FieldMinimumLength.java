package fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core;

public class FieldMinimumLength extends BusinessException {

    public FieldMinimumLength(String fieldName, String fieldValue, int minimumLength) {
        super(fieldName, fieldValue, Integer.toString(minimumLength));
    }

}
