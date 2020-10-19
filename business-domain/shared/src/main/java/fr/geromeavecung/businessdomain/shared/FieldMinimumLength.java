package fr.geromeavecung.businessdomain.shared;

public class FieldMinimumLength extends BusinessException {

    public <T> FieldMinimumLength(String fieldName, T fieldValue, int minimumLength) {
        super(fieldName, fieldValue.toString(), Integer.toString(minimumLength));
    }

}
