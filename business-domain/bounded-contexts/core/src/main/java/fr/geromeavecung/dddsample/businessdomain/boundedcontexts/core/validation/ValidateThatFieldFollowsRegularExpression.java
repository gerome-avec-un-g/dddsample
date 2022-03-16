package fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateThatFieldFollowsRegularExpression {

    private ValidateThatFieldFollowsRegularExpression() {
        /*
         * utility class
         */
    }

    public static String validate(String fieldName, String fieldValue, String regex) {
        if (fieldValue == null) {
            throw new FieldIsRequired(fieldName);
        }
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(fieldValue);
        if (!matcher.matches()) {
            throw new FieldDoesNotMatchRegularExpression(fieldName, fieldValue, regex);
        }
        return fieldValue;
    }

}
