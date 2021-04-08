package fr.geromeavecung.dddsample.resume;

import com.lowagie.text.DocumentException;
import fr.geromeavecung.exposition.presentation.BooksActionForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.xhtmlrenderer.pdf.ITextRenderer;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/resume")
public class ResumeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(fr.geromeavecung.dddsample.books.BooksController.class);
    public static final List<String> SKILLS = Arrays.asList("langages", "technologies", "frameworks",
            "cicd", "database", "process", "english");
    public static final List<String> MISSIONS = Arrays.asList("bnp", "cnp", "egencia", "sgss", "generali", "cdn", "axaBanque", "axaGie", "progesys", "caisseEpargne");
    public static final List<String> FORMATIONS = Arrays.asList("formationJava", "licence", "deug", "bac");
    public static final List<String> INTERESTS = Arrays.asList("programming", "sports", "leisure");

    private final SpringTemplateEngine templateEngine;

    @Autowired
    public ResumeController(SpringTemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    @GetMapping
    public ModelAndView html(@AuthenticationPrincipal UserDetails userDetails, HttpServletResponse response) throws DocumentException, IOException {
        ModelAndView modelAndView = new ModelAndView("resume/resume-pdf");
        //ModelAndView modelAndView = new ModelAndView("resume/resume-pdf-material-web-component");
        modelAndView.addObject("skills", SKILLS);
        modelAndView.addObject("missions", MISSIONS);
        modelAndView.addObject("formations", FORMATIONS);
        modelAndView.addObject("interests", INTERESTS);
        return modelAndView;
    }

    @GetMapping("/print")
    public void print(@AuthenticationPrincipal UserDetails userDetails, HttpServletResponse response) throws DocumentException, IOException {
        Context context = new Context();
        context.setLocale(Locale.GERMAN);
        context.setVariable("skills", Arrays.asList("langages", "technologies", "frameworks",
                "cicd", "database", "english"));
        context.setVariable("missions", Arrays.asList("bnp", "cnp", "egencia", "sgss", "generali", "cdn", "axaBanque", "axaGie", "progesys", "caisseEpargne"));
        context.setVariable("formations", Arrays.asList("formationJava", "licence", "deug", "bac"));
        context.setVariable("interests", Arrays.asList("programming", "sports", "leisure"));
        String html = templateEngine.process("resume/resume-pdf", context);
        ServletOutputStream outputStream = response.getOutputStream();
        try {
            response.setContentType("application/pdf; charset=UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename=CV-2021.pdf");
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(html);
            renderer.layout();
            renderer.createPDF(outputStream);
        } catch (Exception e) {
            LOGGER.error("An error occurred on sending PDF to browser", e);
        } finally {
            try {
                outputStream.flush();
                outputStream.close();
            } catch (Exception ex) {
                LOGGER.error("An error occurred on flushing or closing outputStream", ex);
            }
        }

    }

}

