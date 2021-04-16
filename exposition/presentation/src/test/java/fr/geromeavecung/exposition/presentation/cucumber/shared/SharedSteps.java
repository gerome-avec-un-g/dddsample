package fr.geromeavecung.exposition.presentation.cucumber.shared;

import fr.geromeavecung.businessdomain.shared.BusinessException;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
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
    public String stringType(String cell) {
        return cell;
    }

    @When("Timestamp is {string}")
    public void when_timestamp_is(String timestamp) {
        timestampsInMemory.setTimestamp(timestamp);
    }

    @When("Next identifier is {string}")
    public void when_next_identifier_is(String uuid) {
        identifiersInMemory.setUuid(uuid);
    }

    @Then("i have an error {string} with message {string}")
    public void then_i_have_an_error_with_message(String className, String message) {
        assertThat(sharedState.getActualException())
                .isInstanceOf(BusinessException.class)
                .hasMessage(message);
        assertThat(sharedState.getActualException().getClass().getSimpleName()).isEqualTo(className);
    }

}
