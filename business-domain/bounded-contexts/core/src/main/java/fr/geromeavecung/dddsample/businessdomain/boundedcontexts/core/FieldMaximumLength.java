package fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core;

public class FieldMaximumLength extends BusinessException {

    public FieldMaximumLength(String fieldName, String fieldValue, int maximumLength) {
        super(fieldName, fieldValue, Integer.toString(maximumLength));
    }

}
