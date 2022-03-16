package fr.geromeavecung.dddsample;

import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core.BusinessException;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core.Identifier;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.users.User;
import fr.geromeavecung.dddsample.businessdomain.usecases.authorsusecases.ALibrarianAddsAnAuthor;
import fr.geromeavecung.dddsample.businessdomain.usecases.authorsusecases.ALibrarianListsAllAuthors;
import fr.geromeavecung.dddsample.businessdomain.usecases.authorsusecases.AuthorCreationForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

@Controller
@RequestMapping("/authors")
public class AuthorsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BooksController.class);

    private final ALibrarianListsAllAuthors aLibrarianListsAllAuthors;

    private final ALibrarianAddsAnAuthor aLibrarianAddsAnAuthor;

    public AuthorsController(ALibrarianListsAllAuthors aLibrarianListsAllAuthors, ALibrarianAddsAnAuthor aLibrarianAddsAnAuthor) {
        this.aLibrarianListsAllAuthors = aLibrarianListsAllAuthors;
        this.aLibrarianAddsAnAuthor = aLibrarianAddsAnAuthor;
    }

    @GetMapping
    public ModelAndView authors(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        ModelAndView modelAndView = new ModelAndView("authors");
        User connectedUser = new User(Identifier.from("893b586d-7f89-46e7-9f1b-a8f351ccf5a7"));
        modelAndView.addObject("authorsListPage", aLibrarianListsAllAuthors.execute(connectedUser));
        modelAndView.addAllObjects(model.asMap());
        if (!modelAndView.getModelMap().containsAttribute("authorCreationForm")) {
            modelAndView.addObject("authorCreationForm", new AuthorCreationForm());
        }
        return modelAndView;
    }

    @PostMapping
    public RedirectView authorCreationFormPost(@ModelAttribute AuthorCreationForm authorCreationForm, RedirectAttributes redirectAttributes) {
        try {
            User connectedUser = new User(Identifier.from("893b586d-7f89-46e7-9f1b-a8f351ccf5a7"));
            aLibrarianAddsAnAuthor.execute(connectedUser, authorCreationForm);
            //redirectAttributes.addFlashAttribute("success", "bookCreationSuccess");
        } catch (BusinessException businessException) {
            LOGGER.error("POST /authors", businessException);
            redirectAttributes.addFlashAttribute("authorCreationForm", authorCreationForm);
            redirectAttributes.addFlashAttribute("businessError", businessException);
        }
        return new RedirectView("/authors", true);
    }

}
