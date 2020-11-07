package fr.geromeavecung.dddsample.books;

import fr.geromeavecung.exposition.presentation.BooksPresentationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BooksController {

    private final BooksPresentationService booksPresentationService;

    @Autowired
    public BooksController(BooksPresentationService booksPresentationService) {
        this.booksPresentationService = booksPresentationService;
    }

    @GetMapping("/books")
    public ModelAndView homePage() {
        ModelAndView modelAndView = new ModelAndView("books");
        modelAndView.addObject("books", booksPresentationService.displayBooks());
        return modelAndView;
    }

}
