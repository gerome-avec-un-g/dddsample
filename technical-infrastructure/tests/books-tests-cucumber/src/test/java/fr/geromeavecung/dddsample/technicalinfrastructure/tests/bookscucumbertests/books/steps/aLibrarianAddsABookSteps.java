package fr.geromeavecung.dddsample.technicalinfrastructure.tests.bookscucumbertests.books.steps;

import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.books.AddABook;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.books.Book;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.books.Title;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core.Identifier;
import fr.geromeavecung.dddsample.businessdomain.usecases.booksusecases.ALibrarianAddsABook;
import fr.geromeavecung.dddsample.businessdomain.usecases.booksusecases.BookCreationForm;
import fr.geromeavecung.dddsample.technicalinfrastructure.tests.bookscucumbertests.books.repositories.BooksForCucumber;
import fr.geromeavecung.dddsample.technicalinfrastructure.tests.bookscucumbertests.books.repositories.NonRandomIdentifierForCucumber;
import fr.geromeavecung.dddsample.technicalinfrastructure.tests.bookscucumbertests.shared.SharedState;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class aLibrarianAddsABookSteps {

    private final BooksForCucumber booksForCucumber;

    private final ALibrarianAddsABook aLibrarianAddsABook;

    private final SharedState sharedState;

    @Autowired
    public aLibrarianAddsABookSteps(BooksForCucumber booksForCucumber, SharedState sharedState, NonRandomIdentifierForCucumber nonRandomIdentifierForCucumber) {
        this.booksForCucumber = booksForCucumber;
        this.sharedState = sharedState;
        aLibrarianAddsABook = new ALibrarianAddsABook(new AddABook(booksForCucumber), nonRandomIdentifierForCucumber);
    }

    @When("the user tries to add a book")
    public void the_user_adds_a_book(DataTable dataTable) {
        sharedState.setActualException(null);
        try {
            for (Map<String, String> columns : dataTable.asMaps(String.class, String.class)) {
                BookCreationForm bookCreationForm = new BookCreationForm();
                bookCreationForm.setTitle(columns.get("title"));
                bookCreationForm.setAuthor(columns.get("author identifier"));
                bookCreationForm.setType(Book.Type.valueOf(columns.get("type")));
                aLibrarianAddsABook.execute(sharedState.getLoggedInUser(), bookCreationForm);
            }
        } catch (Exception exception) {
            sharedState.setActualException(exception);
        }
    }

    @Then("the book is added")
    public void theBookIsAdded(DataTable dataTable) {
        sharedState.assertThatNoExceptionIsThrown();
        try {
            for (Map<String, String> columns : dataTable.asMaps(String.class, String.class)) {
                Identifier identifier = Identifier.from(columns.get("identifier"));
                // FIXME .get()
                Optional<Book> optionalBook = booksForCucumber.read(identifier);
                assertThat(optionalBook).isPresent();
                assertThat(optionalBook.get()).usingRecursiveComparison().isEqualTo(Book.create(identifier, new Title(columns.get("title")), Identifier.from(columns.get("author identifier")), Book.Type.valueOf(columns.get("type"))));
            }
        } catch (Exception exception) {
            sharedState.setActualException(exception);
        }
    }

}
