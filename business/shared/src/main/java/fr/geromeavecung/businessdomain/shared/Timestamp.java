package fr.geromeavecung.businessdomain.shared;

import java.time.LocalDateTime;

public class Timestamp {

    private final LocalDateTime value;

    public static Timestamp generate(LocalDateTime localDateTime) {
        return new Timestamp(localDateTime);
    }

    private Timestamp(LocalDateTime localDateTime) {
        this.value = FieldValidator.required("timestamp", localDateTime);
    }

}
