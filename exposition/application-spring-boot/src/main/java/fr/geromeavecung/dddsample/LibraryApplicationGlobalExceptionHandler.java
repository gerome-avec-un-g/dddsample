package fr.geromeavecung.dddsample;

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

//    @ExceptionHandler(Exception.class)
//    public ModelAndView notFoundHandler(Exception exception) {
//        LOGGER.error("Resource not found: ", exception);
//        return new ModelAndView("not-found");
//    }

}