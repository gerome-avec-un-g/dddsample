package fr.geromeavecung.exposition.presentation.cucumber.shared;

import fr.geromeavecung.businessdomain.users.User;
import io.cucumber.spring.CucumberTestContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static org.assertj.core.api.Assertions.assertThat;

@Component
@Scope(CucumberTestContext.SCOPE_CUCUMBER_GLUE)
public class SharedState {

    private Exception actualException;

    private User loggedInUser;

    public Exception getActualException() {
        return actualException;
    }

    public void setActualException(Exception actualException) {
        this.actualException = actualException;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public void assertThatNoExceptionIsThrown() {
        assertThat(actualException).isNull();
    }

}
