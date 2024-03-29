package fr.geromeavecung.dddsample.technicalinfrastructure.tests.authorscucumbertests;

import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core.Identifier;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core.Identifiers;
import io.cucumber.spring.CucumberTestContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository
@Scope(CucumberTestContext.SCOPE_CUCUMBER_GLUE)
public class IdentifiersInMemory implements Identifiers {

    private static final Identifier INITIAL_IDENTIFIER = Identifier.from("271be4e7-aab9-4944-ab45-e1bf75d94dac");

    private Identifier nextIdentifier = INITIAL_IDENTIFIER;

    @Override
    public Identifier generateNewIdentifier() {
        return nextIdentifier;
    }

    public void setNextIdentifier(String uuid) {
        this.nextIdentifier = Identifier.from(uuid);
    }

}
