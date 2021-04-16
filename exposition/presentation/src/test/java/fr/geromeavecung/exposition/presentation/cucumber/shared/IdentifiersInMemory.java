package fr.geromeavecung.exposition.presentation.cucumber.shared;

import fr.geromeavecung.businessdomain.shared.Identifier;
import fr.geromeavecung.businessdomain.shared.Identifiers;
import io.cucumber.spring.CucumberTestContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@Scope(CucumberTestContext.SCOPE_CUCUMBER_GLUE)
public class IdentifiersInMemory implements Identifiers {

    private String uuid = "271be4e7-aab9-4944-ab45-e1bf75d94dac";

    @Override
    public Identifier generate() {
        return Identifier.generate(UUID.fromString(uuid));
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

}
