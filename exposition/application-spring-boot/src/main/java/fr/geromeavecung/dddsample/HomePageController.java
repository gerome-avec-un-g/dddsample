package fr.geromeavecung.dddsample;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomePageController {

    @GetMapping("/")
    public ModelAndView homePage() {
        // TODO chapter 2.4
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("appName", "awesome library mvc hot swap");
        return modelAndView;
    }
}