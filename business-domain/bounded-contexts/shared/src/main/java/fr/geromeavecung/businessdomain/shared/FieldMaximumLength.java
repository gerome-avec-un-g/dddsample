package fr.geromeavecung.businessdomain.shared;

public class FieldMaximumLength extends BusinessException {

    public FieldMaximumLength(String fieldName, String fieldValue, int maximumLength) {
        super(fieldName, fieldValue, Integer.toString(maximumLength));
    }

}
