package fr.geromeavecung.businessdomain.shared;

public class FieldMinimumLength extends BusinessException {

    public FieldMinimumLength(String fieldName, String fieldValue, int minimumLength) {
        super(fieldName, fieldValue, Integer.toString(minimumLength));
    }

}
