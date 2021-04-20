package fr.geromeavecung.dddsample.infrastructure.persistenceinmemory;

import fr.geromeavecung.businessdomain.shared.Timestamp;
import fr.geromeavecung.businessdomain.shared.Timestamps;

import java.time.ZonedDateTime;

public class TimestampsNow implements Timestamps {

    @Override
    public Timestamp generate() {
        return Timestamp.generate(ZonedDateTime.now());
    }

}
