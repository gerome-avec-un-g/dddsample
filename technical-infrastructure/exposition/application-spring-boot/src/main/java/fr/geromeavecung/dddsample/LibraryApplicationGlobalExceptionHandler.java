package fr.geromeavecung.dddsample;

import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core.NotFound;
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

    @ExceptionHandler(NotFound.class)
    public ModelAndView notFoundHandler(NotFound notFound) {
        LOGGER.error("Resource not found: ", notFound);
        ModelAndView modelAndView = new ModelAndView("not-found");
        modelAndView.addObject("error", notFound);
        return modelAndView;
    }

}