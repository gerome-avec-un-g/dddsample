package fr.geromeavecung.dddsample.books;

import fr.geromeavecung.businessdomain.books.Author;
import fr.geromeavecung.businessdomain.books.Book;
import fr.geromeavecung.businessdomain.books.BookAlreadyExists;
import fr.geromeavecung.businessdomain.books.Title;
import fr.geromeavecung.businessdomain.shared.BusinessException;
import fr.geromeavecung.exposition.presentation.BookSummary;
import fr.geromeavecung.exposition.presentation.BooksPresentationService;
import fr.geromeavecung.exposition.presentation.CreateBookForm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class BooksControllerTest {

    private MockMvc mockMvc;

    @Mock
    private BooksPresentationService booksPresentationService;

    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new BooksController(booksPresentationService))
                .setViewResolvers(new NoCircularViewPathTestViewResolver())
                .build();
    }

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
                .andExpect(model().attribute("error", nullValue()));
    }

    @Test
    void bookCreationGet_redirect_after_error() throws Exception {
        CreateBookForm createBookForm = new CreateBookForm();
        createBookForm.setAuthor("abc");
        createBookForm.setTitle("def");
        Book book = Book.create(Title.create("abc"), Author.create("def"));
        BusinessException businessException = new BookAlreadyExists(book);

        mockMvc.perform(get("/book-creation")
                .flashAttr("createBookForm", createBookForm)
                .flashAttr("error", businessException))
                .andExpect(status().isOk())
                .andExpect(view().name("book-creation"))
                .andExpect(model().attribute("createBookForm", createBookForm))
                .andExpect(model().attribute("success", nullValue()))
                .andExpect(model().attribute("error", businessException));
    }

    @Test
    void bookCreationPost_success() throws Exception {
        CreateBookForm createBookForm = new CreateBookForm();
        createBookForm.setAuthor("abc");
        createBookForm.setTitle("def");

        mockMvc.perform(post("/book-creation").flashAttr("createBookForm", createBookForm))
                .andExpect(status().is3xxRedirection());

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

        Book book = Book.create(Title.create("abc"), Author.create("def"));
        doThrow(new BookAlreadyExists(book)).when(booksPresentationService).createBook(expectedCreateBookForm);

        mockMvc.perform(post("/book-creation").flashAttr("createBookForm", createBookForm))
                .andExpect(status().is3xxRedirection());
    }

}