package fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core;

public class NotFoundException extends BusinessException {

    public NotFoundException(String resourceName, String resourceIdentifier) {
        super(resourceName, resourceIdentifier);
    }

}
