package fr.geromeavecung.dddsample.books;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BooksController {

    @GetMapping("/books")
    public ModelAndView homePage() {
        ModelAndView modelAndView = new ModelAndView("books");
        modelAndView.addObject("books", "awesome library mvc");
        return modelAndView;
    }

}
