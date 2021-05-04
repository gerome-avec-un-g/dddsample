package fr.geromeavecung.businessdomain.shared;

import java.util.UUID;

public class Identifier {

    private final UUID value;

    public static Identifier from(UUID uuid) {
        return new Identifier(uuid);
    }

    private Identifier(UUID value) {
        this.value = value;
    }
}
