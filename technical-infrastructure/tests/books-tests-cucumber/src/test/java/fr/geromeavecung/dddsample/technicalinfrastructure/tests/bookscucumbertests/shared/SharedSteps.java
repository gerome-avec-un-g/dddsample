package fr.geromeavecung.dddsample.technicalinfrastructure.tests.bookscucumbertests.shared;

import fr.geromeavecung.dddsample.technicalinfrastructure.tests.bookscucumbertests.shared.repositories.TimestampsInMemory;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core.BusinessException;
import fr.geromeavecung.dddsample.technicalinfrastructure.tests.bookscucumbertests.shared.repositories.IdentifiersInMemory;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class SharedSteps {

    private final TimestampsInMemory timestampsInMemory;

    private final IdentifiersInMemory identifiersInMemory;

    private final SharedState sharedState;

    @Autowired
    public SharedSteps(TimestampsInMemory timestampsInMemory, IdentifiersInMemory identifiersInMemory, SharedState sharedState) {
        this.timestampsInMemory = timestampsInMemory;
        this.identifiersInMemory = identifiersInMemory;
        this.sharedState = sharedState;
    }

    @DataTableType(replaceWithEmptyString = "[blank]")
    public String simulatesEmptyStringsInCucumberTables(String cell) {
        return cell;
    }

    @Given("Today is {int}-{int}-{int}")
    public void given_today_is(int year, int month, int dayOfMonth) {
        timestampsInMemory.setDate(year, month, dayOfMonth);
    }

    @Given("The time is {int}:{int}:{int}")
    public void given_the_hour_is(int hour, int minute, int second) {
        timestampsInMemory.setTime(hour, minute, second);
    }

    @Given("Next identifier is {string}")
    public void when_next_identifier_is(String uuid) {
        identifiersInMemory.setNextIdentifier(uuid);
    }

    @Then("i have an error {string} with parameters {string}")
    public void then_i_have_an_error_with_message(String className, String message) {
        assertThat(sharedState.getActualException()).isInstanceOf(BusinessException.class);
        assertThat(sharedState.getActualException().getClass().getSimpleName()).isEqualTo(className);
        assertThat(sharedState.getActualException())
                .isInstanceOf(BusinessException.class)
                .hasMessage(message);
    }

}
