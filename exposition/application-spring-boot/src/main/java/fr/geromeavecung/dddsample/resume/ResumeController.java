package fr.geromeavecung.dddsample.resume;

import com.lowagie.text.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.xhtmlrenderer.pdf.ITextRenderer;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/resume")
public class ResumeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(fr.geromeavecung.dddsample.books.BooksController.class);

    private final SpringTemplateEngine templateEngine;

    @Autowired
    public ResumeController(SpringTemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    @GetMapping("/print")
    public void print(@AuthenticationPrincipal UserDetails userDetails, HttpServletResponse response) throws DocumentException, IOException {
        Context context = new Context();
        //context.setVariable("books", books);
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

