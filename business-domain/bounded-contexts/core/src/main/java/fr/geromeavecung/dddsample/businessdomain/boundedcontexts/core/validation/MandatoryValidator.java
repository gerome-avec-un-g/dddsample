package fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core.validation;

public class MandatoryValidator {

    private MandatoryValidator() {
        // utility class
    }

    public static <T> T validate(String fieldName, T fieldValue) {
        if (fieldValue == null) {
            throw new FieldIsMandatory(fieldName);
        }
        return fieldValue;
    }

}
