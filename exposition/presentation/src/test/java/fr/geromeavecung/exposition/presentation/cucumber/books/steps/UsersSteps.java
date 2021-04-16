package fr.geromeavecung.exposition.presentation.cucumber.books.steps;

import fr.geromeavecung.businessdomain.users.User;
import fr.geromeavecung.exposition.presentation.cucumber.shared.SharedState;
import io.cucumber.java.en.Given;
import org.springframework.beans.factory.annotation.Autowired;

public class UsersSteps {

    private final SharedState sharedState;

    @Autowired
    public UsersSteps(SharedState sharedState) {
        this.sharedState = sharedState;
    }

    @Given("a librarian")
    public void a_librarian() {
        sharedState.setLoggedInUser(new User("123"));
    }

}
