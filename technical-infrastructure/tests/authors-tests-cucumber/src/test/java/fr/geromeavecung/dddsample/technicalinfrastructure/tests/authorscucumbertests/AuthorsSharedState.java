package fr.geromeavecung.dddsample.technicalinfrastructure.tests.authorscucumbertests;

import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.users.User;
import io.cucumber.spring.CucumberTestContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static org.assertj.core.api.Assertions.assertThat;

@Component
@Scope(CucumberTestContext.SCOPE_CUCUMBER_GLUE)
public class AuthorsSharedState {

    // FIXME move into sharedstep ?

    private Exception actualException;

    private User connectedUser;

    public Exception getActualException() {
        return actualException;
    }

    public void setActualException(Exception actualException) {
        this.actualException = actualException;
    }

    public User getConnectedUser() {
        return connectedUser;
    }

    public void setConnectedUser(User connectedUser) {
        this.connectedUser = connectedUser;
    }

    public void assertThatNoExceptionIsThrown() {
        assertThat(actualException).isNull();
    }

}
