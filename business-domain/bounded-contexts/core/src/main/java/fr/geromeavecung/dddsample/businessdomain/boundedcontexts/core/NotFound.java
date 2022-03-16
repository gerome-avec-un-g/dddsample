package fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core;

public class NotFound extends BusinessException {

    public NotFound(String resourceName, Identifier resourceIdentifier) {
        super(resourceName, resourceIdentifier.toString());
    }

}
