package fr.geromeavecung.dddsample;

import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core.Identifier;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.books.Author;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.books.Book;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.books.BookAlreadyExists;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.books.Title;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core.BusinessException;
import fr.geromeavecung.dddsample.books.BooksController;
import fr.geromeavecung.exposition.presentation.BookCreationForm;
import fr.geromeavecung.exposition.presentation.BookSummary;
import fr.geromeavecung.exposition.presentation.BookSummaryTable;
import fr.geromeavecung.exposition.presentation.BooksPresentationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
@ActiveProfiles("local")
class BooksControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BooksPresentationService booksPresentationService;

    @MockBean
    private LibraryApplicationPropertiesConfiguration libraryApplicationPropertiesConfiguration;

    @WithMockUser(value = "buzz")
    @Test
    void books() throws Exception {
        List<BookSummary> expectedBooks = new ArrayList<>();
        expectedBooks.add(new BookSummary(new Book(Identifier.from("e0917866-bf12-4008-a710-c48ae05042cb"), Title.create("abc"), Author.create("def"), Book.Type.FICTION)));
        BookSummaryTable bookSummaryTable = new BookSummaryTable(expectedBooks);
        when(booksPresentationService.displayBooks()).thenReturn(bookSummaryTable);

        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(view().name("books"))
                .andExpect(model().attribute("bookSummaryTable", bookSummaryTable))
                .andExpect(content().string(containsString("href=\"/books/creation\"")));
    }

    @WithMockUser(value = "buzz")
    @Test
    void booksCreationGet_first_time() throws Exception {
        mockMvc.perform(get("/books/creation"))
                .andExpect(status().isOk())
                .andExpect(view().name("book-creation"))
                // FIXME equals needed ? .andExpect(model().attribute("bookCreationForm", new BookCreationForm()))
                .andExpect(model().attribute("types", Arrays.stream(Book.Type.values()).collect(Collectors.toSet())))
                .andExpect(model().attribute("success", nullValue()))
                .andExpect(model().attribute("businessError", nullValue()));
    }

    @WithMockUser(value = "buzz")
    @Test
    void booksCreationGet_redirect_after_success() throws Exception {
        mockMvc.perform(get("/books/creation").flashAttr("success", "bookCreationSuccess"))
                .andExpect(status().isOk())
                .andExpect(view().name("book-creation"))
                // FIXME equals needed ? .andExpect(model().attribute("bookCreationForm", new BookCreationForm()))
                .andExpect(model().attribute("success", "bookCreationSuccess"))
                .andExpect(model().attribute("businessError", nullValue()))
                .andExpect(content().string(containsString("Le livre est cr"/*éé*/)));
    }

    @WithMockUser(value = "buzz")
    @Test
    void booksCreationGet_redirect_after_error() throws Exception {
        BookCreationForm bookCreationForm = new BookCreationForm();
        bookCreationForm.setAuthor("abc");
        bookCreationForm.setTitle("def");
        bookCreationForm.setType(Book.Type.FICTION);
        Book book = Book.create(Identifier.from("fb235778-36c0-49ed-aad3-16f617a51a9f"), Title.create("abc"), Author.create("def"), Book.Type.FICTION);
        BusinessException businessException = new BookAlreadyExists(book);

        mockMvc.perform(get("/books/creation")
                .flashAttr("bookCreationForm", bookCreationForm)
                .flashAttr("businessError", businessException))
                .andExpect(status().isOk())
                .andExpect(view().name("book-creation"))
                .andExpect(model().attribute("bookCreationForm", bookCreationForm))
                .andExpect(model().attribute("success", nullValue()))
                .andExpect(model().attribute("businessError", businessException))
                .andExpect(content().string(containsString("Le livre abc de def existe d"/*éjà*/)))
                .andExpect(content().string(containsString("value=\"abc\"")))
                .andExpect(content().string(containsString("value=\"def\"")));
    }

    @WithMockUser(value = "buzz")
    @Test
    void booksCreationPost_success() throws Exception {
        BookCreationForm bookCreationForm = new BookCreationForm();
        bookCreationForm.setAuthor("abc");
        bookCreationForm.setTitle("def");
        bookCreationForm.setType(Book.Type.FICTION);

        mockMvc.perform(post("/books/creation").flashAttr("bookCreationForm", bookCreationForm))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/books/creation"))
                .andExpect(flash().attribute("success", "bookCreationSuccess"));

        verify(booksPresentationService).createBook(bookCreationForm);
    }

    @WithMockUser(value = "buzz")
    @Test
    void booksCreationPost_error() throws Exception {
        BookCreationForm bookCreationForm = new BookCreationForm();
        bookCreationForm.setAuthor("abc");
        bookCreationForm.setTitle("def");
        bookCreationForm.setType(Book.Type.FICTION);

        Book book = Book.create(Identifier.from("fe35e912-0008-4f3b-bf23-3641f55ec1a2"), Title.create("abc"), Author.create("def"), Book.Type.FICTION);
        BookAlreadyExists bookAlreadyExists = new BookAlreadyExists(book);
        doThrow(bookAlreadyExists).when(booksPresentationService).createBook(bookCreationForm);

        mockMvc.perform(post("/books/creation").flashAttr("bookCreationForm", bookCreationForm))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/books/creation"))
                .andExpect(flash().attribute("businessError", bookAlreadyExists))
                .andExpect(flash().attribute("bookCreationForm", bookCreationForm));
    }

    // TODO more test for security/roles

}