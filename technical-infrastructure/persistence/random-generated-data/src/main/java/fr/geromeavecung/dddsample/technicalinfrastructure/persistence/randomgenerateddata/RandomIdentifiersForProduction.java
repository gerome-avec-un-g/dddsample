package fr.geromeavecung.dddsample.technicalinfrastructure.persistence.randomgenerateddata;

import fr.geromeavecung.businessdomain.shared.Identifier;
import fr.geromeavecung.businessdomain.shared.Identifiers;

import java.util.UUID;

public class RandomIdentifiersForProduction implements Identifiers {

    @Override
    public Identifier generateNewIdentifier() {
        return Identifier.from(UUID.randomUUID());
    }

}
