package fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core;

public class InvalidIdentifier extends BusinessException {

    public InvalidIdentifier(String uuidRepresentation) {
        super(uuidRepresentation);
    }

}
