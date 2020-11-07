package fr.geromeavecung.dddsample.books;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SimpleController {

    //@Value("${spring.application.name}")
    //String appName;
 
    @GetMapping("/appName")
    public String homePage(Model model) {
        model.addAttribute("appName", "appName");
        return "home";
    }
}