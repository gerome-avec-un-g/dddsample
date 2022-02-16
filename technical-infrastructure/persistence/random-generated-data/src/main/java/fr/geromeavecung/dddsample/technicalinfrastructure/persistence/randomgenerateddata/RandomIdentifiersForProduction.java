package fr.geromeavecung.dddsample.technicalinfrastructure.persistence.randomgenerateddata;

import fr.geromeavecung.businessdomain.shared.Identifier;
import fr.geromeavecung.businessdomain.shared.Identifiers;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class RandomIdentifiersForProduction implements Identifiers {

    @Override
    public Identifier generateNewIdentifier() {
        return Identifier.from(UUID.randomUUID());
    }

}
