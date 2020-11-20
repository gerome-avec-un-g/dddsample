package fr.geromeavecung.dddsample;

import fr.geromeavecung.businessdomain.books.Author;
import fr.geromeavecung.businessdomain.books.Book;
import fr.geromeavecung.businessdomain.books.BookAlreadyExists;
import fr.geromeavecung.businessdomain.books.Title;
import fr.geromeavecung.businessdomain.shared.BusinessException;
import fr.geromeavecung.exposition.presentation.BookSummary;
import fr.geromeavecung.exposition.presentation.BooksPresentationService;
import fr.geromeavecung.exposition.presentation.CreateBookForm;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BooksController.class)
class BooksControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BooksPresentationService booksPresentationService;

    @Test
    void books() throws Exception {
        Set<BookSummary> expectedBooks = new HashSet<>();
        expectedBooks.add(new BookSummary("abc", "def"));
        when(booksPresentationService.displayBooks()).thenReturn(expectedBooks);

        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(view().name("books"))
                .andExpect(model().attribute("books", expectedBooks));
    }

    @Test
    void bookCreationGet_first_time() throws Exception {
        mockMvc.perform(get("/book-creation"))
                .andExpect(status().isOk())
                .andExpect(view().name("book-creation"))
                .andExpect(model().attribute("createBookForm", new CreateBookForm()))
                .andExpect(model().attribute("success", nullValue()))
                .andExpect(model().attribute("error", nullValue()));
    }

    @Test
    void bookCreationGet_redirect_after_success() throws Exception {
        mockMvc.perform(get("/book-creation").flashAttr("success", true))
                .andExpect(status().isOk())
                .andExpect(view().name("book-creation"))
                .andExpect(model().attribute("createBookForm", new CreateBookForm()))
                .andExpect(model().attribute("success", true))
                .andExpect(model().attribute("error", nullValue()))
                .andExpect(content().string(containsString("Le livre est cr")));
    }

    @Test
    void bookCreationGet_redirect_after_error() throws Exception {
        CreateBookForm createBookForm = new CreateBookForm();
        createBookForm.setAuthor("abc");
        createBookForm.setTitle("def");
        Book book = Book.create(Title.create("abc"), Author.create("def"), Book.Type.FICTION);
        BusinessException businessException = new BookAlreadyExists(book);

        mockMvc.perform(get("/book-creation")
                .flashAttr("createBookForm", createBookForm)
                .flashAttr("error", businessException))
                .andExpect(status().isOk())
                .andExpect(view().name("book-creation"))
                .andExpect(model().attribute("createBookForm", createBookForm))
                .andExpect(model().attribute("success", nullValue()))
                .andExpect(model().attribute("error", businessException))
                .andExpect(content().string(containsString("Le livre abc de def existe d")))
                .andExpect(content().string(containsString("value=\"abc\"")))
                .andExpect(content().string(containsString("value=\"def\"")));
    }

    @Test
    void bookCreationPost_success() throws Exception {
        CreateBookForm createBookForm = new CreateBookForm();
        createBookForm.setAuthor("abc");
        createBookForm.setTitle("def");

        mockMvc.perform(post("/book-creation").flashAttr("createBookForm", createBookForm))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/book-creation"))
                .andExpect(flash().attribute("success", true));

        verify(booksPresentationService).createBook(createBookForm);
    }

    @Test
    void bookCreationPost_error() throws Exception {
        CreateBookForm createBookForm = new CreateBookForm();
        createBookForm.setAuthor("abc");
        createBookForm.setTitle("def");
        CreateBookForm expectedCreateBookForm = new CreateBookForm();
        expectedCreateBookForm.setAuthor("abc");
        expectedCreateBookForm.setTitle("def");

        Book book = Book.create(Title.create("abc"), Author.create("def"), Book.Type.FICTION);
        BookAlreadyExists bookAlreadyExists = new BookAlreadyExists(book);
        doThrow(bookAlreadyExists).when(booksPresentationService).createBook(expectedCreateBookForm);

        mockMvc.perform(post("/book-creation").flashAttr("createBookForm", createBookForm))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/book-creation"))
                .andExpect(flash().attribute("error", bookAlreadyExists))
                .andExpect(flash().attribute("createBookForm", createBookForm));
    }

}