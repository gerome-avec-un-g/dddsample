package fr.geromeavecung.dddsample.technicalinfrastructure.persistence.randomgenerateddata;

import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core.Identifier;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core.Identifiers;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class RandomIdentifiersForProduction implements Identifiers {

    @Override
    public Identifier generateNewIdentifier() {
        return new Identifier(UUID.randomUUID());
    }

}
