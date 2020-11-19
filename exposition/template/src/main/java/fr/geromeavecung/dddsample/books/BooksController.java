package fr.geromeavecung.dddsample.books;

import fr.geromeavecung.businessdomain.shared.BusinessException;
import fr.geromeavecung.exposition.presentation.BooksPresentationService;
import fr.geromeavecung.exposition.presentation.CreateBookForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class BooksController {

    private final Logger logger = LoggerFactory.getLogger(BooksController.class);

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
    public ModelAndView bookCreationGet(Model model) {
        ModelAndView modelAndView = new ModelAndView("book-creation");
        modelAndView.addAllObjects(model.asMap());
        if (!modelAndView.getModelMap().containsAttribute("createBookForm")) {
            modelAndView.addObject("createBookForm", new CreateBookForm());
        }
        return modelAndView;
    }

    @PostMapping("/book-creation")
    public RedirectView bookCreationPost(@ModelAttribute CreateBookForm createBookForm, RedirectAttributes redirectAttributes) {
        try {
            booksPresentationService.createBook(createBookForm);
            redirectAttributes.addFlashAttribute("success", true);
            return new RedirectView("/book-creation", true);
        } catch (BusinessException businessException) {
            logger.warn("TODO", businessException);
            redirectAttributes.addFlashAttribute("createBookForm", createBookForm);
            redirectAttributes.addFlashAttribute("error", businessException);
            return new RedirectView("/book-creation", true);
        }
    }

}
