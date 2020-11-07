package fr.geromeavecung.dddsample.books;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SimpleController {

    @GetMapping("/appName")
    public ModelAndView homePage() {
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("appName", "awesome library mvc");
        return modelAndView;
    }
}