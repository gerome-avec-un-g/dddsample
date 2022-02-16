package fr.geromeavecung.businessdomain.shared;

import java.util.Arrays;
import java.util.List;

public abstract class BusinessException extends RuntimeException {

    protected BusinessException(Object... parameters) {
        super(Arrays.toString(parameters));
    }

    public String getKey() {
        return this.getClass().getSimpleName();
    }

    public abstract List<String> getParameters();

}
