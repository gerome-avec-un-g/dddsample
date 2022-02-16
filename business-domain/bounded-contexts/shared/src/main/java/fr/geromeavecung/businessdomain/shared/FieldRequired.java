package fr.geromeavecung.businessdomain.shared;

public class FieldRequired extends BusinessException {

    public FieldRequired(String fieldName) {
        super(fieldName);
    }

}
