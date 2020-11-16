package fr.geromeavecung.dddsample.books;

import fr.geromeavecung.exposition.presentation.BooksPresentationService;
import fr.geromeavecung.exposition.presentation.CreateBookForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BooksController {

    private final BooksPresentationService booksPresentationService;

    @Autowired
    public BooksController(BooksPresentationService booksPresentationService) {
        this.booksPresentationService = booksPresentationService;
    }

    @GetMapping("/books")
    public ModelAndView books() {
        ModelAndView modelAndView = new ModelAndView("books");
        modelAndView.addObject("books", booksPresentationService.displayBooks());
        return modelAndView;
    }

    @GetMapping("/book-creation")
    public ModelAndView bookCreationGet() {
        ModelAndView modelAndView = new ModelAndView("book-creation");
        modelAndView.addObject("createBookForm", new CreateBookForm());
        return modelAndView;
    }

    @PostMapping("/book-creation")
    public ModelAndView bookCreationPost(@ModelAttribute CreateBookForm createBookForm) {
        booksPresentationService.createBook(createBookForm);
        return new ModelAndView("redirect:book-creation");
    }

}
