import fr.geromeavecung.businessdomain.users.User;
import fr.geromeavecung.exposition.presentation.BooksPresentationService;
import fr.geromeavecung.exposition.presentation.BusinessExceptionResponse;
import fr.geromeavecung.exposition.presentation.CreateBookRequest;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class CucumberStepsDefinitions {

    private User user;

    private List<CreateBookRequest> createBookRequests = new ArrayList<CreateBookRequest>();

    private Exception exception;

    private BooksPresentationService booksPresentationService = new BooksPresentationService();

    @Given("a librarian")
    public void a_librarian() {
        user = new User("123");
    }

    @When("the user adds a book")
    public void the_user_adds_a_book(DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);

        try {
            for (Map<String, String> columns : rows) {
                booksPresentationService.createBook(new CreateBookRequest(columns.get("title")));
            }
        } catch (Exception exception) {
            this.exception = exception;
        }
    }

    @Then("i have an error message {string}")
    public void i_have_an_error_message(String message) {
        assertThat(exception)
                .isInstanceOf(BusinessExceptionResponse.class)
                .hasMessage(message);
    }

}
