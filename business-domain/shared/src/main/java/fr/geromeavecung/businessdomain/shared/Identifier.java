package fr.geromeavecung.businessdomain.shared;

import java.time.LocalDateTime;
import java.util.UUID;

public class Identifier {

    private final UUID value;

    public static Identifier generate(UUID uuid) {
        return new Identifier(uuid);
    }

    private Identifier(UUID value) {
        this.value = value;
    }
}
