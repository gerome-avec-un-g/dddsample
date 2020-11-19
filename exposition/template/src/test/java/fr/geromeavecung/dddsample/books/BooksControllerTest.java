package fr.geromeavecung.dddsample.books;

import fr.geromeavecung.exposition.presentation.BooksPresentationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
        mockMvc.perform(get("/books")).andExpect(status().isOk());
    }

}