package fr.geromeavecung.dddsample.technicalinfrastructure.tests.bookscucumbertests.shared;

import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.authors.Author;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.books.Book;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.books.Title;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core.FirstName;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core.Identifier;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core.LastName;
import fr.geromeavecung.dddsample.technicalinfrastructure.tests.bookscucumbertests.books.repositories.AuthorsForCucumber;
import fr.geromeavecung.dddsample.technicalinfrastructure.tests.bookscucumbertests.books.repositories.BooksForCucumber;
import fr.geromeavecung.dddsample.technicalinfrastructure.tests.bookscucumbertests.shared.repositories.TimestampsInMemory;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core.BusinessException;
import fr.geromeavecung.dddsample.technicalinfrastructure.tests.bookscucumbertests.shared.repositories.IdentifiersInMemory;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class SharedSteps {

    // TODO real common shared ?

    private final TimestampsInMemory timestampsInMemory;

    private final IdentifiersInMemory identifiersInMemory;

    private final BooksForCucumber booksForCucumber;

    private final AuthorsForCucumber authorsForCucumber;

    private final SharedState sharedState;

    @Autowired
    public SharedSteps(TimestampsInMemory timestampsInMemory, IdentifiersInMemory identifiersInMemory, BooksForCucumber booksForCucumber, AuthorsForCucumber authorsForCucumber, SharedState sharedState) {
        this.timestampsInMemory = timestampsInMemory;
        this.identifiersInMemory = identifiersInMemory;
        this.booksForCucumber = booksForCucumber;
        this.authorsForCucumber = authorsForCucumber;
        this.sharedState = sharedState;
    }

    @DataTableType(replaceWithEmptyString = "[empty]")
    public String simulates_empty_string_in_cucumber_tables(String cell) {
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
        assertThat(sharedState.getActualException().getClass().getSimpleName()).isEqualTo(className.replaceAll(" ", ""));
        assertThat(sharedState.getActualException())
                .isInstanceOf(BusinessException.class)
                .hasMessage(message);
    }

    @Given("a library with books")
    public void aLibraryWithBooks(DataTable dataTable) {
        try {
            for (Map<String, String> columns : dataTable.asMaps(String.class, String.class)) {
                Book book = new Book(Identifier.from(columns.get("identifier")), Title.create(columns.get("title")), Identifier.from(columns.get("author identifier")), Book.Type.valueOf(columns.get("type")));
                booksForCucumber.save(book);
            }
        } catch (Exception exception) {
            sharedState.setActualException(exception);
        }
    }

    @Given("a library with authors")
    public void a_library_with_authors(DataTable dataTable) {
        try {
            for (Map<String, String> columns : dataTable.asMaps(String.class, String.class)) {
                authorsForCucumber.save(Author.read(Identifier.from(columns.get("identifier")), new FirstName(columns.get("first name")), new LastName(columns.get("last name"))));
            }
        } catch (Exception exception) {
            sharedState.setActualException(exception);
        }
    }

}
