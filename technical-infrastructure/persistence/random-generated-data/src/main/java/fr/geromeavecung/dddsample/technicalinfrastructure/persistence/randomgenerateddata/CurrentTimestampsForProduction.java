package fr.geromeavecung.dddsample.technicalinfrastructure.persistence.randomgenerateddata;

import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core.Timestamp;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core.Timestamps;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;

@Repository
public class CurrentTimestampsForProduction implements Timestamps {

    @Override
    public Timestamp now() {
        return Timestamp.generate(ZonedDateTime.now());
    }

}
