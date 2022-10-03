package fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core;

import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core.validation.MandatoryValidator;

import java.util.UUID;

public record Identifier(UUID value) {

    public static Identifier from(String uuidRepresentation) {
        try {
            UUID uuid = UUID.fromString(uuidRepresentation);
            return new Identifier(uuid);
        } catch (Exception exception) {
            throw new InvalidIdentifier(uuidRepresentation);
        }
    }

    public Identifier(UUID value) {
        this.value = MandatoryValidator.validate("identifier", value);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
