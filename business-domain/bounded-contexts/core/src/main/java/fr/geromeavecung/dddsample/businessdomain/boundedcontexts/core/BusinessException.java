package fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core;

import java.util.Arrays;
import java.util.List;

public abstract class BusinessException extends RuntimeException {

    private final List<String> parameters;

    protected BusinessException(String... parameters) {
        super(Arrays.toString(parameters));
        this.parameters = Arrays.asList(parameters);
    }

    public String getKey() {
        return this.getClass().getSimpleName();
    }

    public List<String> getParameters() {
        return parameters;
    }

}
