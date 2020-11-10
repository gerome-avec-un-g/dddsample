package fr.geromeavecung.exposition.presentation;

import fr.geromeavecung.businessdomain.users.User;
import io.cucumber.java.en.Given;
import org.springframework.beans.factory.annotation.Autowired;

public class UsersSteps {

    private final CucumberState cucumberState;

    @Autowired
    public UsersSteps(CucumberState cucumberState) {
        this.cucumberState = cucumberState;
    }

    @Given("a librarian")
    public void a_librarian() {
        cucumberState.user = new User("123");
    }

}
