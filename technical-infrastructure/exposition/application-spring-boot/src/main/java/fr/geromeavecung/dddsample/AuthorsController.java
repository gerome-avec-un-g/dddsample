package fr.geromeavecung.dddsample;

import fr.geromeavecung.dddsample.books.BooksController;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core.Identifier;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.users.User;
import fr.geromeavecung.dddsample.businessdomain.usecases.authorsusecases.ALibrarianListsAllAuthors;
import fr.geromeavecung.dddsample.businessdomain.usecases.booksusecases.BooksActionForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/authors")
public class AuthorsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BooksController.class);

    private final ALibrarianListsAllAuthors aLibrarianListsAllAuthors;

    public AuthorsController(ALibrarianListsAllAuthors aLibrarianListsAllAuthors) {
        this.aLibrarianListsAllAuthors = aLibrarianListsAllAuthors;
    }

    @GetMapping
    public ModelAndView authors(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        ModelAndView modelAndView = new ModelAndView("authors");
        User connectedUser = new User(Identifier.from("893b586d-7f89-46e7-9f1b-a8f351ccf5a7"));
        modelAndView.addObject("authorsListPage", aLibrarianListsAllAuthors.execute(connectedUser));
        modelAndView.addAllObjects(model.asMap());
        if (!modelAndView.getModelMap().containsAttribute("booksActionForm")) {
            modelAndView.addObject("booksActionForm", new BooksActionForm());
        }
        return modelAndView;
    }

}
