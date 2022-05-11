package fr.geromeavecung.dddsample.technicalinfrastructure.tests.authorscucumbertests;

import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.authors.Author;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core.BusinessException;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core.FirstName;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core.Identifier;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core.LastName;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.users.User;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class AuthorsSharedSteps {

    private final AuthorsSharedState authorsSharedState;

    private final AuthorsForCucumber authorsForCucumber;

    @Autowired
    public AuthorsSharedSteps(AuthorsSharedState authorsSharedState, AuthorsForCucumber authorsForCucumber) {
        this.authorsSharedState = authorsSharedState;
        this.authorsForCucumber = authorsForCucumber;
    }

    @DataTableType(replaceWithEmptyString = "[empty]")
    public String simulates_empty_string_in_cucumber_tables(String cell) {
        return cell;
    }

    @Given("the connected user is a librarian")
    public void the_connected_user_is_a_librarian() {
        authorsSharedState.setConnectedUser(new User(Identifier.from("83f20b0e-5d95-4d07-a0cf-e92c80d71f2a")));
    }

    @Given("the existing authors are")
    public void the_existing_authors_are(DataTable dataTable) {
        for (Map<String, String> columns : dataTable.asMaps(String.class, String.class)) {
            Identifier identifier = Identifier.from(columns.get("identifier"));
            authorsForCucumber.save(Author.read(identifier, new FirstName(columns.get("first name")), new LastName(columns.get("last name"))));
        }
    }

    @Then("the system raises the error {string} with parameters {string}")
    public void then_i_have_an_error_with_message(String errorKey, String errorParameters) {
        assertThat(authorsSharedState.getActualException()).isInstanceOf(BusinessException.class);
        assertThat(authorsSharedState.getActualException().getClass().getSimpleName()).isEqualTo(errorKey.replaceAll(" ", ""));
        assertThat(authorsSharedState.getActualException())
                .isInstanceOf(BusinessException.class)
                .hasMessage(errorParameters);
    }

}
