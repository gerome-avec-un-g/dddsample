package fr.geromeavecung.exposition.presentation.cucumber.shared.repositories;

import fr.geromeavecung.businessdomain.shared.Identifier;
import fr.geromeavecung.businessdomain.shared.Identifiers;
import io.cucumber.spring.CucumberTestContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@Scope(CucumberTestContext.SCOPE_CUCUMBER_GLUE)
public class IdentifiersInMemory implements Identifiers {

    private static final Identifier INITIAL_IDENTIFIER = Identifier.from(UUID.fromString("271be4e7-aab9-4944-ab45-e1bf75d94dac"));

    private Identifier nextIdentifier = INITIAL_IDENTIFIER;

    @Override
    public Identifier generateNextIdentifier() {
        return nextIdentifier;
    }

    public void setNextIdentifier(String uuid) {
        this.nextIdentifier = Identifier.from(UUID.fromString(uuid));
    }

}
