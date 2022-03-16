package fr.geromeavecung.dddsample.technicalinfrastructure.tests.bookscucumbertests.books.steps;

import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.authors.ReadAuthors;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.books.ListAllBooks;
import fr.geromeavecung.dddsample.businessdomain.usecases.booksusecases.ALibrarianListsAllBooks;
import fr.geromeavecung.dddsample.businessdomain.usecases.booksusecases.BookSummary;
import fr.geromeavecung.dddsample.businessdomain.usecases.booksusecases.BookSummaryTable;
import fr.geromeavecung.dddsample.technicalinfrastructure.tests.bookscucumbertests.books.repositories.AuthorsForCucumber;
import fr.geromeavecung.dddsample.technicalinfrastructure.tests.bookscucumbertests.books.repositories.BooksForCucumber;
import fr.geromeavecung.dddsample.technicalinfrastructure.tests.bookscucumbertests.shared.SharedState;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ALibrarianListsAllBooksSteps {

    private final ALibrarianListsAllBooks aLibrarianListsAllBooks;

    private final SharedState sharedState;

    private BookSummaryTable bookSummaryTable;

    @Autowired
    public ALibrarianListsAllBooksSteps(BooksForCucumber booksForCucumber, SharedState sharedState, AuthorsForCucumber authorsForCucumber) {
        this.sharedState = sharedState;
        aLibrarianListsAllBooks = new ALibrarianListsAllBooks(new ListAllBooks(booksForCucumber), new ReadAuthors(authorsForCucumber));
    }


    @When("the user tries to display all books")
    public void theUserTriesToDisplayAllBooks() {
        sharedState.setActualException(null);
        try {
            bookSummaryTable = aLibrarianListsAllBooks.execute(sharedState.getLoggedInUser());
        } catch (Exception exception) {
            sharedState.setActualException(exception);
        }
    }

    @Then("the books are displayed")
    public void theBooksAreDisplayed(DataTable dataTable) {
        sharedState.assertThatNoExceptionIsThrown();
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        assertThat(bookSummaryTable.getBookSummaries().size()).isEqualTo(rows.size());
        for (int i = 0; i < rows.size(); i++) {
            Map<String, String> row = rows.get(i);
            BookSummary bookSummary = bookSummaryTable.getBookSummaries().get(i);
            assertThat(bookSummary.getIdentifier()).isEqualTo(row.get("identifier"));
            assertThat(bookSummary.getTitle()).isEqualTo(row.get("title"));
            assertThat(bookSummary.getAuthorFirstName()).isEqualTo(row.get("author first name"));
            assertThat(bookSummary.getAuthorLastName()).isEqualTo(row.get("author last name"));
            assertThat(bookSummary.getType()).isEqualTo(row.get("type"));
        }
    }


}
