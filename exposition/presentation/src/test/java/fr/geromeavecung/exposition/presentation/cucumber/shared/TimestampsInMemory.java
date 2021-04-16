package fr.geromeavecung.exposition.presentation.cucumber.shared;

import fr.geromeavecung.businessdomain.shared.Timestamp;
import fr.geromeavecung.businessdomain.shared.Timestamps;
import io.cucumber.spring.CucumberTestContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
@Scope(CucumberTestContext.SCOPE_CUCUMBER_GLUE)
public class TimestampsInMemory implements Timestamps {

    private String timestamp = "2015-08-04T10:11:30";

    @Override
    public Timestamp generate() {
        return Timestamp.generate(LocalDateTime.parse(timestamp));
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

}
