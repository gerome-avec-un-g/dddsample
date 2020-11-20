package fr.geromeavecung.dddsample;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
class GlobalDefaultExceptionHandler {

//    @ExceptionHandler(Exception.class)
//    public ModelAndView globalExceptionHandler(Exception exception) {
//        ModelAndView modelAndView = new ModelAndView("error");
//        modelAndView.addObject("exception", exception);
//        return modelAndView;
//    }

    // TODO global 404
    // TODO global 500

}