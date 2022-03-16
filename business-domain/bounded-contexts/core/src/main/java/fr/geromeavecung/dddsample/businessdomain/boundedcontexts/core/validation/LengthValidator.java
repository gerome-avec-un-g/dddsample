package fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core.validation;

public class LengthValidator {

    private LengthValidator() {
        // utility class
    }

    public static String validate(String fieldName, String fieldValue, int minimumLength, int maximumLength) {
        if (fieldValue == null) {
            throw new FieldIsMandatory(fieldName);
        }
        if (fieldValue.length() < minimumLength) {
            throw new FieldIsTooShort(fieldName, fieldValue, minimumLength);
        }
        if (fieldValue.length() > maximumLength) {
            throw new FieldIsTooLong(fieldName, fieldValue, maximumLength);
        }
        return fieldValue;
    }
}
