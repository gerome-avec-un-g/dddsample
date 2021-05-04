package fr.geromeavecung.exposition.presentation.cucumber.shared.repositories;

import fr.geromeavecung.businessdomain.shared.Timestamp;
import fr.geromeavecung.businessdomain.shared.Timestamps;
import io.cucumber.spring.CucumberTestContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;

@Repository
@Scope(CucumberTestContext.SCOPE_CUCUMBER_GLUE)
public class TimestampsInMemory implements Timestamps {

    private ZonedDateTime zonedDateTime = ZonedDateTime.parse("2020-12-20T17:23:51.193133300+02:00[Europe/Paris]");

    @Override
    public Timestamp now() {
        return Timestamp.generate(zonedDateTime);
    }

    public void setTime(int hour, int minute, int second) {
        this.zonedDateTime = zonedDateTime.with(LocalTime.of ( hour, minute, second,123));
    }

    public void setDate(int year, int month, int dayOfMonth) {
        this.zonedDateTime = zonedDateTime.with(LocalDate.of(year, month, dayOfMonth));
    }

}
