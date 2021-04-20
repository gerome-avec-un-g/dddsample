package fr.geromeavecung.exposition.presentation.cucumber.books.steps;

import fr.geromeavecung.businessdomain.users.User;
import fr.geromeavecung.exposition.presentation.cucumber.shared.SharedState;
import io.cucumber.java.en.Given;
import org.springframework.beans.factory.annotation.Autowired;

public class UsersLoggedInSteps {

    private final SharedState sharedState;

    @Autowired
    public UsersLoggedInSteps(SharedState sharedState) {
        this.sharedState = sharedState;
    }

    @Given("a customer")
    public void a_customer() {
        sharedState.setLoggedInUser(new User("456"));
    }

    @Given("a librarian")
    public void a_librarian() {
        sharedState.setLoggedInUser(new User("123"));
    }

    @Given("an application administrator is logged in")
    public void an_application_administrator_is_logged_in() {
        sharedState.setLoggedInUser(new User("789"));
    }

}
