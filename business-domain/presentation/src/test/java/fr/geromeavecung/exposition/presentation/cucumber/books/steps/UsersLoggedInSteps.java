package fr.geromeavecung.exposition.presentation.cucumber.books.steps;

import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core.Identifier;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.users.User;
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
        sharedState.setLoggedInUser(new User(Identifier.from("ed427aed-2963-42b3-abd2-a1815bd67e8c")));
    }

    @Given("a librarian")
    public void a_librarian() {
        sharedState.setLoggedInUser(new User(Identifier.from("83f20b0e-5d95-4d07-a0cf-e92c80d71f2a")));
    }

    @Given("an application administrator is logged in")
    public void an_application_administrator_is_logged_in() {
        sharedState.setLoggedInUser(new User(Identifier.from("50ba5be3-1cb5-4f84-a239-ca170bb63bb7")));
    }

}
