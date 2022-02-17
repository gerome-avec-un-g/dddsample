package fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core;

public class FieldValidator {

    private FieldValidator() {
        /*
         * utility class
         */
    }

    public static <T> T required(String fieldName, T fieldValue) {
        if (fieldValue == null) {
            throw new FieldRequired(fieldName);
        }
        return fieldValue;
    }

    public static String length(String fieldName, String fieldValue, int minimumLength, int maximumLength) {
        required(fieldName, fieldValue);
        if (fieldValue.length() < minimumLength) {
            throw new FieldMinimumLength(fieldName, fieldValue, minimumLength);
        }
        if (fieldValue.length() > maximumLength) {
            throw new FieldMaximumLength(fieldName, fieldValue, maximumLength);
        }
        return fieldValue;
    }
}
