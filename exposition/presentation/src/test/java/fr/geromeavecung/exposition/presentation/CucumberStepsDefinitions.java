package fr.geromeavecung.exposition.presentation;

import fr.geromeavecung.businessdomain.books.Author;
import fr.geromeavecung.businessdomain.books.Book;
import fr.geromeavecung.businessdomain.books.Books;
import fr.geromeavecung.businessdomain.books.BooksService;
import fr.geromeavecung.businessdomain.books.Title;
import fr.geromeavecung.businessdomain.users.User;
import fr.geromeavecung.exposition.orchestration.BooksOrchestrationService;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class CucumberStepsDefinitions {

    private final Books books = new BooksInMemory();

    private final BooksPresentationService booksPresentationService = new BooksPresentationService(new BooksOrchestrationService(new BooksService(books)));

    private Exception actualException;

    private User user;

    @Given("a librarian")
    public void a_librarian() {
        user = new User("123");
    }

    @When("the user adds a book")
    public void the_user_adds_a_book(DataTable table) {
        List<Map<String, String>> rows = table.asMaps(String.class, String.class);

        try {
            for (Map<String, String> columns : rows) {
                booksPresentationService.createBook(new CreateBookRequest(columns.get("title"), columns.get("author")));
            }
        } catch (Exception exception) {
            this.actualException = exception;
        }
    }

    @Then("i have an error message {string}")
    public void i_have_an_error_message(String message) {
        assertThat(actualException)
                .isInstanceOf(BusinessExceptionResponse.class)
                .hasMessage(message);
    }

    @DataTableType(replaceWithEmptyString = "[blank]")
    public String stringType(String cell) {
        return cell;
    }

    @Then("the book is added")
    public void theBookIsAdded(DataTable table) {
        List<Map<String, String>> rows = table.asMaps(String.class, String.class);

        try {
            for (Map<String, String> columns : rows) {
                booksPresentationService.createBook(new CreateBookRequest(columns.get("title"), columns.get("author")));
                assertThat(books.all()).contains(Book.create(Title.create(columns.get("title")), Author.create(columns.get("author"))));
            }
        } catch (Exception exception) {
            this.actualException = exception;
        }

        // for passing cases we should always check that no exceptions are thrown
        assertThat(actualException).isNull();
    }
}
