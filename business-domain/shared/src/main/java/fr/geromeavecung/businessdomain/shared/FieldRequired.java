package fr.geromeavecung.businessdomain.shared;

public class FieldRequired extends BusinessException {

    public <T> FieldRequired(String fieldName) {
        super(fieldName);
    }

}
