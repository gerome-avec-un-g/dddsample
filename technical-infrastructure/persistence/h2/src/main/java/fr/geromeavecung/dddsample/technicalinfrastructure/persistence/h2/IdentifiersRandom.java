package fr.geromeavecung.dddsample.technicalinfrastructure.persistence.h2;

import fr.geromeavecung.businessdomain.shared.Identifier;
import fr.geromeavecung.businessdomain.shared.Identifiers;

import java.util.UUID;

public class IdentifiersRandom implements Identifiers {

    @Override
    public Identifier generateNextIdentifier() {
        return Identifier.from(UUID.randomUUID());
    }

}
