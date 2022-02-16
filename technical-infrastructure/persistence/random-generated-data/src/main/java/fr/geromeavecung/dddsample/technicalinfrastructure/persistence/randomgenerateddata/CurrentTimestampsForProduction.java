package fr.geromeavecung.dddsample.technicalinfrastructure.persistence.randomgenerateddata;

import fr.geromeavecung.businessdomain.shared.Timestamp;
import fr.geromeavecung.businessdomain.shared.Timestamps;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;

@Repository
public class CurrentTimestampsForProduction implements Timestamps {

    @Override
    public Timestamp now() {
        return Timestamp.generate(ZonedDateTime.now());
    }

}
