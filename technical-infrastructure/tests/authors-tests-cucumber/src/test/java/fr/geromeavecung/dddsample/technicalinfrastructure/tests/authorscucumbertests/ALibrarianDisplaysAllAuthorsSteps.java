package fr.geromeavecung.dddsample.technicalinfrastructure.tests.authorscucumbertests;

import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.authors.ReadAuthors;
import fr.geromeavecung.dddsample.businessdomain.usecases.authorsusecases.ALibrarianListsAllAuthors;
import fr.geromeavecung.dddsample.businessdomain.usecases.authorsusecases.AuthorRow;
import fr.geromeavecung.dddsample.businessdomain.usecases.authorsusecases.AuthorsListPage;
import fr.geromeavecung.dddsample.businessdomain.usecases.authorsusecases.InformationMessage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ALibrarianDisplaysAllAuthorsSteps {

    private final ALibrarianListsAllAuthors aLibrarianListsAllAuthors;

    private final AuthorsSharedState authorsSharedState;

    private AuthorsListPage authorsListPage;

    @Autowired
    public ALibrarianDisplaysAllAuthorsSteps(AuthorsForCucumber authorsForCucumber, AuthorsSharedState authorsSharedState) {
        this.authorsSharedState = authorsSharedState;
        aLibrarianListsAllAuthors = new ALibrarianListsAllAuthors(new ReadAuthors(authorsForCucumber));
    }

    @When("the connected user lists all authors")
    public void the_connected_user_lists_all_authors() {
        authorsSharedState.setActualException(null);
        try {
            authorsListPage = aLibrarianListsAllAuthors.execute(authorsSharedState.getConnectedUser());
        } catch (Exception exception) {
            authorsSharedState.setActualException(exception);
        }
    }

    @Then("the author list is")
    public void the_author_is_added(DataTable dataTable) {
        authorsSharedState.assertThatNoExceptionIsThrown();
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        assertThat(authorsListPage.getAuthorsRow().size()).isEqualTo(rows.size());
        for (int i = 0; i < rows.size(); i++) {
            Map<String, String> row = rows.get(i);
            AuthorRow authorRow = authorsListPage.getAuthorsRow().get(i);
            assertThat(authorRow.getFirstName()).isEqualTo(row.get("first name"));
            assertThat(authorRow.getLastName()).isEqualTo(row.get("last name"));
        }
    }

    @Then("the information message is {string}")
    public void the_information_message_is(String informationMessage) {
        authorsSharedState.assertThatNoExceptionIsThrown();
        assertThat(authorsListPage.getInformationMessage().toString()).isEqualTo(informationMessage.replaceAll(" ", "_").toUpperCase());
    }

    @Then("there is no information message")
    public void there_is_no_information_message() {
        authorsSharedState.assertThatNoExceptionIsThrown();
        assertThat(authorsListPage.getInformationMessage()).isEqualTo(InformationMessage.NO_INFORMATION_MESSAGE);
    }

}
