package fr.geromeavecung.businessdomain.shared;

public class NotFoundException extends BusinessException {

    public NotFoundException(String resourceName, String resourceIdentifier) {
        super(resourceName, resourceIdentifier);
    }

}
