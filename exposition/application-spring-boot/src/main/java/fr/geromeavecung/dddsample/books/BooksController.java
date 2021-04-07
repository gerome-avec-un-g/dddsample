package fr.geromeavecung.dddsample.books;

import com.lowagie.text.DocumentException;
import fr.geromeavecung.businessdomain.books.Book;
import fr.geromeavecung.businessdomain.shared.BusinessException;
import fr.geromeavecung.dddsample.LibraryApplicationPropertiesConfiguration;
import fr.geromeavecung.exposition.presentation.BookCreationForm;
import fr.geromeavecung.exposition.presentation.BookSummary;
import fr.geromeavecung.exposition.presentation.BooksActionForm;
import fr.geromeavecung.exposition.presentation.BooksPresentationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
//@RequestMapping("/books")
public class BooksController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BooksController.class);

    private final BooksPresentationService booksPresentationService;

    private final LibraryApplicationPropertiesConfiguration libraryApplicationPropertiesConfiguration;

    @Autowired
    public BooksController(BooksPresentationService booksPresentationService, LibraryApplicationPropertiesConfiguration libraryApplicationPropertiesConfiguration) {
        this.booksPresentationService = booksPresentationService;
        this.libraryApplicationPropertiesConfiguration = libraryApplicationPropertiesConfiguration;
    }

    @GetMapping("/books")
    public ModelAndView books(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        ModelAndView modelAndView = new ModelAndView("books");
        System.out.println(libraryApplicationPropertiesConfiguration.toString() + " " + userDetails);
        modelAndView.addObject("books", booksPresentationService.displayBooks());
        modelAndView.addAllObjects(model.asMap());
        if (!modelAndView.getModelMap().containsAttribute("booksActionForm")) {
            modelAndView.addObject("booksActionForm", new BooksActionForm());
        }
        return modelAndView;
    }

    @GetMapping("/books-print")
    public void print(@AuthenticationPrincipal UserDetails userDetails, HttpServletResponse response) throws DocumentException, IOException {
        String html = parseThymeleafTemplate();
        ServletOutputStream outputStream = null;
        try  {
            response.setContentType("application/pdf; charset=UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename=books.pdf");
            outputStream = response.getOutputStream();
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

    private String parseThymeleafTemplate() {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        Set<BookSummary> books = booksPresentationService.displayBooks();
        Context context = new Context();
        context.setVariable("books", books);

        return templateEngine.process("templates/books-print", context);
    }

    @GetMapping("/books/{id}")
    public ModelAndView booksById(@PathVariable("id") String id, @AuthenticationPrincipal UserDetails userDetails) {
        ModelAndView modelAndView = new ModelAndView("book-detail");
        modelAndView.addObject("bookDetail", booksPresentationService.bookDetail(id));
        return modelAndView;
    }

    @GetMapping("/books/creation")
    public ModelAndView bookCreationGet(Model model) {
        ModelAndView modelAndView = new ModelAndView("book-creation");
        modelAndView.addAllObjects(model.asMap());
        if (!modelAndView.getModelMap().containsAttribute("bookCreationForm")) {
            modelAndView.addObject("bookCreationForm", new BookCreationForm());
        }
        modelAndView.addObject("types", Arrays.stream(Book.Type.values()).collect(Collectors.toSet()));
        return modelAndView;
    }

    @PostMapping("/books/creation")
    public RedirectView bookCreationPost(@ModelAttribute BookCreationForm bookCreationForm, RedirectAttributes redirectAttributes) {
        try {
            booksPresentationService.createBook(bookCreationForm);
            redirectAttributes.addFlashAttribute("success", "bookCreationSuccess");
        } catch (BusinessException businessException) {
            LOGGER.error("/books/creation", businessException);
            redirectAttributes.addFlashAttribute("bookCreationForm", bookCreationForm);
            redirectAttributes.addFlashAttribute("businessError", businessException);
        }
        return new RedirectView("/books/creation", true);
    }

    @PostMapping("/books/actions")
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
