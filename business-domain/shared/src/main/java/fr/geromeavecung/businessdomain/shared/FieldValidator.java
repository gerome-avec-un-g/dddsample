package fr.geromeavecung.businessdomain.shared;

public class FieldValidator {

    private FieldValidator() {
        // utility class
    }

    public static <T> T required(String fieldName, T fieldValue) {
        if (fieldValue == null) {
            throw new FieldRequired(fieldName);
        }
        return fieldValue;
    }

    public static String maximumLength(String fieldName, String fieldValue, int maximumLength) {
        required(fieldName, fieldValue);
        if (fieldValue.length() > maximumLength) {
            throw new FieldMaximumLength(fieldName, fieldValue, maximumLength);
        }
        return fieldValue;
    }
}
