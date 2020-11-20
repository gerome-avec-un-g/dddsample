package fr.geromeavecung.dddsample.books;

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

import org.hamcrest.Matcher;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

}