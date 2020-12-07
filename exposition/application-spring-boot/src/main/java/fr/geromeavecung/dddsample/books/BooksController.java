package fr.geromeavecung.dddsample.books;

import fr.geromeavecung.businessdomain.books.Book;
import fr.geromeavecung.businessdomain.shared.BusinessException;
import fr.geromeavecung.exposition.presentation.BooksActionForm;
import fr.geromeavecung.exposition.presentation.BooksPresentationService;
import fr.geromeavecung.exposition.presentation.CreateBookForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Arrays;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/books")
public class BooksController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BooksController.class);

    private final BooksPresentationService booksPresentationService;

    @Autowired
    public BooksController(BooksPresentationService booksPresentationService) {
        this.booksPresentationService = booksPresentationService;
    }

    @GetMapping
    public ModelAndView books(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        ModelAndView modelAndView = new ModelAndView("books");
        System.out.println(userDetails);
        modelAndView.addObject("books", booksPresentationService.displayBooks());
        modelAndView.addAllObjects(model.asMap());
        if (!modelAndView.getModelMap().containsAttribute("booksActionForm")) {
            modelAndView.addObject("booksActionForm", new BooksActionForm());
        }
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView bookCreationGet(Model model) {
        ModelAndView modelAndView = new ModelAndView("book-creation");
        modelAndView.addAllObjects(model.asMap());
        if (!modelAndView.getModelMap().containsAttribute("createBookForm")) {
            modelAndView.addObject("createBookForm", new CreateBookForm());
        }
        modelAndView.addObject("types", Arrays.stream(Book.Type.values()).collect(Collectors.toSet()));
        return modelAndView;
    }

    // TODO post should be on /books or /books/create ? page urls vs rest urls ?
    // TODO @ModelAttribute not mandatory ?
    @PostMapping("/create")
    public RedirectView bookCreationPost(@ModelAttribute CreateBookForm createBookForm, RedirectAttributes redirectAttributes) {
        try {
            booksPresentationService.createBook(createBookForm);
            redirectAttributes.addFlashAttribute("success", "bookCreationSuccess");
        } catch (BusinessException businessException) {
            LOGGER.error("/books/create", businessException);
            redirectAttributes.addFlashAttribute("createBookForm", createBookForm);
            redirectAttributes.addFlashAttribute("businessError", businessException);
        }
        return new RedirectView("/books/create", true);
    }

    @PostMapping("/actions")
    public RedirectView bookActionPost(@ModelAttribute BooksActionForm booksActionForm, RedirectAttributes redirectAttributes) {
        try {
            booksPresentationService.booksAction(booksActionForm);
            redirectAttributes.addFlashAttribute("success", "booksActionSuccess");
        } catch (BusinessException businessException) {
            LOGGER.error("/books/actions: ", businessException);
            redirectAttributes.addFlashAttribute("booksActionForm", booksActionForm);
            redirectAttributes.addFlashAttribute("businessError", businessException);
        }
        return new RedirectView("/books", true);
    }

}
