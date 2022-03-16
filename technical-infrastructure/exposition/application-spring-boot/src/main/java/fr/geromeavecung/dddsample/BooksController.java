package fr.geromeavecung.dddsample;

import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.books.Book;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core.BusinessException;
import fr.geromeavecung.dddsample.businessdomain.usecases.booksusecases.ALibrarianAddsABook;
import fr.geromeavecung.dddsample.businessdomain.usecases.booksusecases.ALibrarianListsAllBooks;
import fr.geromeavecung.dddsample.businessdomain.usecases.booksusecases.BookCreationForm;
import fr.geromeavecung.dddsample.businessdomain.usecases.booksusecases.BooksActionForm;
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
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.xhtmlrenderer.pdf.ITextRenderer;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/books")
public class BooksController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BooksController.class);

    private final SpringTemplateEngine templateEngine;

    private final ALibrarianListsAllBooks aLibrarianListsAllBooks;

    private final ALibrarianAddsABook aLibrarianAddsABook;

    private final LibraryApplicationPropertiesConfiguration libraryApplicationPropertiesConfiguration;

    @Autowired
    public BooksController(SpringTemplateEngine templateEngine, ALibrarianListsAllBooks aLibrarianListsAllBooks, ALibrarianAddsABook aLibrarianAddsABook, LibraryApplicationPropertiesConfiguration libraryApplicationPropertiesConfiguration) {
        this.templateEngine = templateEngine;
        this.aLibrarianListsAllBooks = aLibrarianListsAllBooks;
        this.aLibrarianAddsABook = aLibrarianAddsABook;
        this.libraryApplicationPropertiesConfiguration = libraryApplicationPropertiesConfiguration;
    }

    @GetMapping
    public ModelAndView books(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        LOGGER.info("Charset: " + Charset.defaultCharset().displayName());
        LOGGER.info("Version: " + libraryApplicationPropertiesConfiguration.getVersion());
        ModelAndView modelAndView = new ModelAndView("books");
        modelAndView.addObject("bookSummaryTable", aLibrarianListsAllBooks.execute(null));
        modelAndView.addAllObjects(model.asMap());
        if (!modelAndView.getModelMap().containsAttribute("booksActionForm")) {
            modelAndView.addObject("booksActionForm", new BooksActionForm());
        }
        return modelAndView;
    }

    @GetMapping("/print")
    public void print(@AuthenticationPrincipal UserDetails userDetails, HttpServletResponse response) throws IOException {
        Context context = new Context();
        context.setVariable("bookSummaryTable", aLibrarianListsAllBooks.execute(null));
        String html = templateEngine.process("books-print", context);
        ServletOutputStream outputStream = response.getOutputStream();
        try  {
            response.setContentType("application/pdf; charset=UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename=books.pdf");
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(html);
            renderer.layout();
            renderer.createPDF(outputStream);
        } catch (Exception e)  {
            LOGGER.error("An error occured on sending PDF to browser", e);
        } finally {
            try {
                outputStream.flush();
                outputStream.close();
            } catch (Exception ex) {
                LOGGER.error("An error occured on flushing or closing outputStream", ex);
            }
        }

    }

    @GetMapping("/creation")
    public ModelAndView bookCreationGet(Model model) {
        ModelAndView modelAndView = new ModelAndView("book-creation");
        modelAndView.addAllObjects(model.asMap());
        if (!modelAndView.getModelMap().containsAttribute("bookCreationForm")) {
            modelAndView.addObject("bookCreationForm", new BookCreationForm());
        }
        modelAndView.addObject("types", Arrays.stream(Book.Type.values()).collect(Collectors.toSet()));
        return modelAndView;
    }

    @PostMapping("/creation")
    public RedirectView bookCreationPost(@ModelAttribute BookCreationForm bookCreationForm, RedirectAttributes redirectAttributes) {
        try {
            aLibrarianAddsABook.execute(null, bookCreationForm);
            redirectAttributes.addFlashAttribute("success", "bookCreationSuccess");
        } catch (BusinessException businessException) {
            LOGGER.error("/books/creation", businessException);
            redirectAttributes.addFlashAttribute("bookCreationForm", bookCreationForm);
            redirectAttributes.addFlashAttribute("businessError", businessException);
        }
        return new RedirectView("/books/creation", true);
    }

}
