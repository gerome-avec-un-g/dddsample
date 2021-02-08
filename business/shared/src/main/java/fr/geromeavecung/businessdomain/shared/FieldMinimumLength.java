package fr.geromeavecung.businessdomain.shared;

import java.util.Arrays;
import java.util.List;

public class FieldMinimumLength extends BusinessException {

    private final String fieldName;

    private final String fieldValue;

    private final Integer minimumLength;

    public <T> FieldMinimumLength(String fieldName, String fieldValue, int minimumLength) {
        super(fieldName, fieldValue, minimumLength);
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
        this.minimumLength = minimumLength;
    }

    @Override
    public List<String> getParameters() {
        return Arrays.asList(fieldName, fieldValue, minimumLength.toString());
    }

}
