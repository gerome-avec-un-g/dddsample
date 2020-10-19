import io.cucumber.datatable.DataTable;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CucumberStepsDefinitions {

    @Given("a librarian")
    public void a_librarian() {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("the user adds a book")
    public void the_user_adds_a_book(DataTable dataTable) {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
        // Double, Byte, Short, Long, BigInteger or BigDecimal.
        //
        // For other transformations you can register a DataTableType.
        throw new PendingException();
    }

    @Then("i have an error message {string}")
    public void i_have_an_error_message(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

}
