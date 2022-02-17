package fr.geromeavecung.dddsample;

import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
class LibraryApplicationGlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(LibraryApplicationGlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ModelAndView globalExceptionHandler(Exception exception) {
        LOGGER.error("Global exception caught: ", exception);
        return new ModelAndView("server-error");
    }

    @ExceptionHandler(NotFoundException.class)
    public ModelAndView notFoundHandler(NotFoundException notFoundException) {
        LOGGER.error("Resource not found: ", notFoundException);
        return new ModelAndView("not-found");
    }

}