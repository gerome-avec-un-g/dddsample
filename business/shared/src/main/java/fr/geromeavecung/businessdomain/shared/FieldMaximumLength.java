package fr.geromeavecung.businessdomain.shared;

import java.util.Arrays;
import java.util.List;

public class FieldMaximumLength extends BusinessException {

    private final String fieldName;
    
    private final String fieldValue;
    
    private final Integer maximumLength;
    
    public <T> FieldMaximumLength(String fieldName, String fieldValue, int maximumLength) {
        super(fieldName, fieldValue, maximumLength);
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
        this.maximumLength = maximumLength;
    }

    @Override
    public List<String> getParameters() {
        return Arrays.asList(fieldName, fieldValue, maximumLength.toString());
    }
}
