package fr.geromeavecung.exposition.presentation;

import fr.geromeavecung.businessdomain.books.Author;
import fr.geromeavecung.businessdomain.books.Book;
import fr.geromeavecung.businessdomain.books.BooksService;
import fr.geromeavecung.businessdomain.books.Title;
import fr.geromeavecung.businessdomain.shared.BusinessException;
import fr.geromeavecung.exposition.orchestration.BooksOrchestrationService;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class BooksSteps {

    private final BooksInMemory booksInMemory;

    private final BooksPresentationService booksPresentationService;

    private final CucumberState cucumberState;

    @Autowired
    public BooksSteps(BooksInMemory booksInMemory, CucumberState cucumberState) {
        this.booksInMemory = booksInMemory;
        booksPresentationService = new BooksPresentationService(new BooksOrchestrationService(new BooksService(booksInMemory)));
        this.cucumberState = cucumberState;
    }

    @DataTableType(replaceWithEmptyString = "[blank]")
    public String stringType(String cell) {
        return cell;
    }

    @When("the user tries to add a book")
    public void the_user_adds_a_book(DataTable table) {
        try {
            for (Map<String, String> columns : table.<String, String>asMaps(String.class, String.class)) {
                CreateBookRequest createBookRequest = new CreateBookRequest();
                createBookRequest.setTitle(columns.get("title"));
                createBookRequest.setAuthor(columns.get("author"));
                booksPresentationService.createBook(createBookRequest);
            }
        } catch (Exception exception) {
            cucumberState.actualException = exception;
        }
    }

    @Then("i have an error {string} with message {string}")
    public void i_have_an_error_message(String className, String message) throws ClassNotFoundException {
        assertThat(cucumberState.actualException)
                .isInstanceOf(BusinessException.class)
                .hasMessage(message);
        assertThat(cucumberState.actualException.getClass().getSimpleName()).isEqualTo(className);
    }

    @Then("the book is added")
    public void theBookIsAdded(DataTable table) {
        try {
            for (Map<String, String> columns : table.<String, String>asMaps(String.class, String.class)) {
                assertThat(booksInMemory.all()).contains(Book.create(Title.create(columns.get("title")), Author.create(columns.get("author"))));
            }
        } catch (Exception exception) {
            cucumberState.actualException = exception;
        }

        noExceptionIsThrownForPassingCase();
    }


    @Given("a library with books")
    public void aLibraryWithBooks(DataTable table) {
        try {
            for (Map<String, String> columns : table.<String, String>asMaps(String.class, String.class)) {
                CreateBookRequest createBookRequest = new CreateBookRequest();
                createBookRequest.setTitle(columns.get("title"));
                createBookRequest.setAuthor(columns.get("author"));
                booksPresentationService.createBook(createBookRequest);
            }
        } catch (Exception exception) {
            cucumberState.actualException = exception;
        }
    }

    private Set<BookSummary> bookSummaries;

    @When("the user tries to display all books")
    public void theUserTriesToDisplayAllBooks() {
        try {
            bookSummaries = booksPresentationService.displayBooks();
        } catch (Exception exception) {
            cucumberState.actualException = exception;
        }
    }

    @Then("the books are displayed")
    public void theBooksAreDisplayed(DataTable table) {
        noExceptionIsThrownForPassingCase();
        Set<BookSummary> expected = new HashSet<>();
        for (Map<String, String> columns : table.<String, String>asMaps(String.class, String.class)) {
            expected.add(new BookSummary(columns.get("title"), columns.get("author")));
        }
        assertThat(bookSummaries).isEqualTo(expected);
    }

    private void noExceptionIsThrownForPassingCase() {
        assertThat(cucumberState.actualException).isNull();
    }
}
