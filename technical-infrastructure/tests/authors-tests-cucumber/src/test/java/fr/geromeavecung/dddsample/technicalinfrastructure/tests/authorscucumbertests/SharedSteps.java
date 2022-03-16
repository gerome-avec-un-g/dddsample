package fr.geromeavecung.dddsample.technicalinfrastructure.tests.authorscucumbertests;

import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core.BusinessException;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core.Identifier;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.users.User;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class SharedSteps {

    private final SharedState sharedState;

    @Autowired
    public SharedSteps(SharedState sharedState) {
        this.sharedState = sharedState;
    }

    @DataTableType(replaceWithEmptyString = "[empty]")
    public String simulate_empty_string_in_cucumber_tables(String cell) {
        return cell;
    }

    @Given("the connected user is a librarian")
    public void the_connected_user_is_a_librarian() {
        sharedState.setLoggedInUser(new User(Identifier.from("83f20b0e-5d95-4d07-a0cf-e92c80d71f2a")));
    }

    @Then("the system raises the error {string} with parameters {string}")
    public void then_i_have_an_error_with_message(String errorKey, String errorParameters) {
        assertThat(sharedState.getActualException()).isInstanceOf(BusinessException.class);
        assertThat(sharedState.getActualException().getClass().getSimpleName()).isEqualTo(errorKey.replaceAll(" ", ""));
        assertThat(sharedState.getActualException())
                .isInstanceOf(BusinessException.class)
                .hasMessage(errorParameters);
    }

}
