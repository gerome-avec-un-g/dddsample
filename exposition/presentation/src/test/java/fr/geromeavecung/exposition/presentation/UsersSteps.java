package fr.geromeavecung.exposition.presentation;

import fr.geromeavecung.businessdomain.books.BooksService;
import fr.geromeavecung.businessdomain.users.User;
import fr.geromeavecung.exposition.orchestration.BooksOrchestrationService;
import io.cucumber.java.en.Given;
import org.springframework.beans.factory.annotation.Autowired;

public class UsersSteps {


//    private final BooksInMemory booksInMemory;
//
//    private final BooksPresentationService booksPresentationService;
//
//    private Exception actualException;
//
//    private User user;

    private final CucumberState cucumberState;

    @Autowired
    public UsersSteps(CucumberState cucumberState) {
        this.cucumberState = cucumberState;
        //booksPresentationService = new BooksPresentationService(new BooksOrchestrationService(new BooksService(booksInMemory)));
    }

    @Given("a librarian")
    public void a_librarian() {
        cucumberState.user = new User("123");
    }

}
