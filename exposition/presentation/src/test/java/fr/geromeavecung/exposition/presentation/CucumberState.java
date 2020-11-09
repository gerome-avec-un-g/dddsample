package fr.geromeavecung.exposition.presentation;

import fr.geromeavecung.businessdomain.users.User;
import io.cucumber.spring.ScenarioScope;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
// does not seem to work @ScenarioScope
@Scope("cucumber-glue")
public class CucumberState {

    public CucumberState() {
        System.out.println("CucumberState created");
    }

    Exception actualException;

    User user;

}
