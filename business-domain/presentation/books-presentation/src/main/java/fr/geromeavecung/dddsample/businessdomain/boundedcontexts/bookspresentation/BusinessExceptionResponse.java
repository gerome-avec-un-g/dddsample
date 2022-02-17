package fr.geromeavecung.dddsample.businessdomain.boundedcontexts.bookspresentation;

import java.util.List;

public class BusinessExceptionResponse extends RuntimeException {

    public BusinessExceptionResponse(String exceptionName, List<String> parameters) {
        super(exceptionName + " " + parameters);
    }

}
