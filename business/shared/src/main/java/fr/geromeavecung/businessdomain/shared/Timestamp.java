package fr.geromeavecung.businessdomain.shared;

import java.time.ZonedDateTime;

public class Timestamp {

    private final ZonedDateTime value;

    public static Timestamp generate(ZonedDateTime localDateTime) {
        return new Timestamp(localDateTime);
    }

    private Timestamp(ZonedDateTime localDateTime) {
        this.value = FieldValidator.required("timestamp", localDateTime);
    }

}
