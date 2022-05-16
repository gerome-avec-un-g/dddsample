package fr.geromeavecung.dddsample;

import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core.Identifier;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.users.User;
import fr.geromeavecung.dddsample.businessdomain.usecases.authorsusecases.ALibrarianReadsOneAuthor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Controller
@RequestMapping("/author")
public class AuthorController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BooksController.class);

    private final ALibrarianReadsOneAuthor aLibrarianReadsOneAuthor;

    public AuthorController(ALibrarianReadsOneAuthor aLibrarianReadsOneAuthor) {
        this.aLibrarianReadsOneAuthor = aLibrarianReadsOneAuthor;
    }

    @GetMapping("/{id}")
    public ModelAndView author(@PathVariable("id") String uuidRepresentation, @AuthenticationPrincipal UserDetails userDetails) {
        ModelAndView modelAndView = new ModelAndView("author");
        User connectedUser = new User(new Identifier(UUID.fromString("893b586d-7f89-46e7-9f1b-a8f351ccf5a7")));
        modelAndView.addObject("author", aLibrarianReadsOneAuthor.execute(connectedUser, uuidRepresentation));
        return modelAndView;
    }

}
