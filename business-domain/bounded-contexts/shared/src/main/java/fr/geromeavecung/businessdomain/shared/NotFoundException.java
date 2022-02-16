package fr.geromeavecung.businessdomain.shared;

import java.util.Arrays;
import java.util.List;

public class NotFoundException extends BusinessException {

    private final String resourceName;

    private final String resourceIdentifier;

    public <T> NotFoundException(String resourceName, String resourceIdentifier) {
        super(resourceName, resourceIdentifier);
        this.resourceName = resourceName;
        this.resourceIdentifier = resourceIdentifier;
    }

    @Override
    public List<String> getParameters() {
        return Arrays.asList(resourceName, resourceIdentifier);
    }

}
