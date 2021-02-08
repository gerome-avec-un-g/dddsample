package fr.geromeavecung.businessdomain.shared;

import java.util.Collections;
import java.util.List;

public class FieldRequired extends BusinessException {

    private final String fieldName;

    public FieldRequired(String fieldName) {
        super(fieldName);
        this.fieldName = fieldName;
    }

    @Override
    public List<String> getParameters() {
        return Collections.singletonList(fieldName);
    }
}
