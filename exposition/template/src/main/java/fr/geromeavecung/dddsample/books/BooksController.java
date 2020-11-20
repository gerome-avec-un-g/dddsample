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

    private final Logger LOGGER = LoggerFactory.getLogger(BooksController.class);

    private final BooksPresentationService booksPresentationService;

    @Autowired
    public BooksController(BooksPresentationService booksPresentationService) {
        this.booksPresentationService = booksPresentationService;
    }

    @GetMapping("/books")
    public ModelAndView books() {
        long start = System.currentTimeMillis();
        // TODO generic spring request logging + duration ?
        LOGGER.info("GET /books");
        ModelAndView modelAndView = new ModelAndView("books");
        modelAndView.addObject("books", booksPresentationService.displayBooks());
        LOGGER.info("Completed GET /books in {} ms", System.currentTimeMillis() - start);
        return modelAndView;
    }

    @GetMapping("/book-creation")
    public ModelAndView bookCreationGet(Model model) {
        long start = System.currentTimeMillis();
        LOGGER.info("GET /book-creation");
        ModelAndView modelAndView = new ModelAndView("book-creation");
        modelAndView.addAllObjects(model.asMap());
        if (!modelAndView.getModelMap().containsAttribute("createBookForm")) {
            modelAndView.addObject("createBookForm", new CreateBookForm());
        }
        LOGGER.info("Completed GET /book-creation in {} ms", System.currentTimeMillis() - start);
        return modelAndView;
    }

    @PostMapping("/book-creation")
    public RedirectView bookCreationPost(@ModelAttribute CreateBookForm createBookForm, RedirectAttributes redirectAttributes) {
        long start = System.currentTimeMillis();
        LOGGER.info("POST /book-creation");
        try {
            booksPresentationService.createBook(createBookForm);
            redirectAttributes.addFlashAttribute("success", true);
        } catch (BusinessException businessException) {
            LOGGER.error("TODO", businessException);
            redirectAttributes.addFlashAttribute("createBookForm", createBookForm);
            redirectAttributes.addFlashAttribute("error", businessException);
        }
        RedirectView redirectView = new RedirectView("/book-creation", true);
        LOGGER.info("Completed POST /book-creation in {} ms", System.currentTimeMillis() - start);
        return redirectView;
    }

}
