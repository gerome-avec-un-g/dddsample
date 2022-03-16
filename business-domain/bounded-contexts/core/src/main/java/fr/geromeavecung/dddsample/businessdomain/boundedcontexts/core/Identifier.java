package fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core;

import java.util.Objects;
import java.util.UUID;

public class Identifier {

    private final UUID value;

    public static Identifier from(String uuidRepresentation) {
        return new Identifier(UUID.fromString(uuidRepresentation));
    }

    public static Identifier from(UUID uuid) {
        return new Identifier(uuid);
    }

    private Identifier(UUID value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Identifier that = (Identifier) o;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
