package fr.geromeavecung.exposition.presentation;

import fr.geromeavecung.businessdomain.users.User;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("cucumber-glue") //@ScenarioScope  does not seem to work
public class CucumberState {

    Exception actualException;

    User user;

}
