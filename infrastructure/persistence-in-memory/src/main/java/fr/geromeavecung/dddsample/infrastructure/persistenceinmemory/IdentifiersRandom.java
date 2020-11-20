package fr.geromeavecung.dddsample.infrastructure.persistenceinmemory;

import fr.geromeavecung.businessdomain.shared.Identifier;
import fr.geromeavecung.businessdomain.shared.Identifiers;

import java.util.UUID;

public class IdentifiersRandom implements Identifiers {

    @Override
    public Identifier generate() {
        return Identifier.generate(UUID.randomUUID());
    }

}
