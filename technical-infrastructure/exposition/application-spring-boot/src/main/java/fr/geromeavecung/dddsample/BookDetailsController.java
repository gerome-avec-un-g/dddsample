package fr.geromeavecung.dddsample;

import fr.geromeavecung.dddsample.businessdomain.usecases.booksusecases.ALibrarianDisplaysABookDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/book-details")
public class BookDetailsController {

    private final ALibrarianDisplaysABookDetails aLibrarianDisplaysABookDetails;

    @Autowired
    public BookDetailsController(ALibrarianDisplaysABookDetails aLibrarianDisplaysABookDetails) {
        this.aLibrarianDisplaysABookDetails = aLibrarianDisplaysABookDetails;
    }

    @GetMapping("/{id}")
    public ModelAndView bookDetails(@PathVariable("id") String uuidRepresentation, @AuthenticationPrincipal UserDetails userDetails) {
        ModelAndView modelAndView = new ModelAndView("book-details");
        modelAndView.addObject("bookDetails", aLibrarianDisplaysABookDetails.execute(null, uuidRepresentation));
        return modelAndView;
    }

}
