package fr.geromeavecung.dddsample.technicalinfrastructure.tests.authorscucumbertests;

import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.authors.AddAnAuthor;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.authors.Author;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core.FirstName;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core.Identifier;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core.LastName;
import fr.geromeavecung.dddsample.businessdomain.usecases.authorsusecases.ALibrarianAddsAnAuthor;
import fr.geromeavecung.dddsample.businessdomain.usecases.authorsusecases.AuthorCreationForm;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class ALibrarianAddsAnAuthorSteps {

    private final ALibrarianAddsAnAuthor aLibrarianAddsAnAuthor;

    private final AuthorsForCucumber authorsForCucumber;

    private final AuthorsSharedState authorsSharedState;

    @Autowired
    public ALibrarianAddsAnAuthorSteps(AuthorsForCucumber authorsForCucumber, NonRandomIdentifiersForTesting nonRandomIdentifiersForTesting, AuthorsSharedState authorsSharedState) {
        this.authorsForCucumber = authorsForCucumber;
        this.authorsSharedState = authorsSharedState;
        aLibrarianAddsAnAuthor = new ALibrarianAddsAnAuthor(new AddAnAuthor(authorsForCucumber), nonRandomIdentifiersForTesting);
    }

    @When("the connected user tries to add an author")
    public void the_connected_user_adds_an_author(DataTable dataTable) {
        authorsSharedState.setActualException(null);
        try {
            for (Map<String, String> columns : dataTable.<String, String>asMaps(String.class, String.class)) {
                AuthorCreationForm authorCreationForm = new AuthorCreationForm();
                authorCreationForm.setFirstName(columns.get("first name"));
                authorCreationForm.setLastName(columns.get("last name"));
                aLibrarianAddsAnAuthor.execute(authorsSharedState.getConnectedUser(), authorCreationForm);
            }
        } catch (Exception exception) {
            authorsSharedState.setActualException(exception);
        }
    }

    @Then("the author is added")
    public void the_author_is_added(DataTable dataTable) {
        authorsSharedState.assertThatNoExceptionIsThrown();
        for (Map<String, String> columns : dataTable.<String, String>asMaps(String.class, String.class)) {
            Identifier identifier = Identifier.from(columns.get("identifier"));
            Optional<Author> optionalAuthor = authorsForCucumber.read(identifier);
            assertThat(optionalAuthor).isPresent();
            optionalAuthor.ifPresent(author -> assertThat(author).usingRecursiveComparison().isEqualTo(Author.read(identifier, FirstName.from(columns.get("first name")), LastName.from(columns.get("last name")))));
        }
    }

}
