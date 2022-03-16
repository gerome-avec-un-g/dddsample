package fr.geromeavecung.dddsample.technicalinfrastructure.tests.authorscucumbertests;

import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core.Identifier;
import fr.geromeavecung.dddsample.businessdomain.usecases.authorsusecases.ALibrarianAddsAnAuthor;
import fr.geromeavecung.dddsample.businessdomain.usecases.authorsusecases.AuthorCreationForm;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class AuthorsSteps {

    private final ALibrarianAddsAnAuthor aLibrarianAddsAnAuthor;

    private final AuthorsForCucumber authorsForCucumber;

    private final SharedState sharedState;

    @Autowired
    public AuthorsSteps(AuthorsForCucumber authorsForCucumber, NonRandomIdentifiersForTesting nonRandomIdentifiersForTesting, SharedState sharedState) {
        this.authorsForCucumber = authorsForCucumber;
        this.sharedState = sharedState;
        aLibrarianAddsAnAuthor = new ALibrarianAddsAnAuthor(nonRandomIdentifiersForTesting);
    }

    @When("the user tries to add an author")
    public void the_user_adds_an_author(DataTable table) {
        sharedState.setActualException(null);
        try {
            for (Map<String, String> columns : table.<String, String>asMaps(String.class, String.class)) {
                AuthorCreationForm authorCreationForm = new AuthorCreationForm();
                authorCreationForm.setFirstName(columns.get("first name"));
                authorCreationForm.setLastName(columns.get("last name"));
                aLibrarianAddsAnAuthor.execute(sharedState.getLoggedInUser(), authorCreationForm);
            }
        } catch (Exception exception) {
            sharedState.setActualException(exception);
        }
    }

    @Then("the author is added")
    public void the_author_is_added(DataTable table) {
        sharedState.assertThatNoExceptionIsThrown();
        try {
            for (Map<String, String> columns : table.<String, String>asMaps(String.class, String.class)) {
                Identifier identifier = Identifier.from(columns.get("identifier"));
                assertThat(authorsForCucumber.read(identifier)).isPresent();
                // FIXME .get()
                // FIXME assertThat(authorsForCucumber.read(identifier).get()).usingRecursiveComparison().isEqualTo(Author.read(identifier, FirstName.from(columns.get("first name")), LastName.from(columns.get("last name"))));
            }
        } catch (Exception exception) {
            sharedState.setActualException(exception);
        }
    }

}
