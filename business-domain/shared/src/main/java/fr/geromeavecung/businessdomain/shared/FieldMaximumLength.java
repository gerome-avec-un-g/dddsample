package fr.geromeavecung.businessdomain.shared;

public class FieldMaximumLength extends BusinessException {

    public <T> FieldMaximumLength(String fieldName, T fieldValue, int maximumLength) {
        super(fieldName, fieldValue.toString(), Integer.toString(maximumLength));
    }

}
