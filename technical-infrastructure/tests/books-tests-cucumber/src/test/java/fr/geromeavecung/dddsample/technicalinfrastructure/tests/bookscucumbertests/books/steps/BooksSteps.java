package fr.geromeavecung.dddsample.technicalinfrastructure.tests.bookscucumbertests.books.steps;

import fr.geromeavecung.dddsample.technicalinfrastructure.tests.bookscucumbertests.books.repositories.BooksForCucumber;
import fr.geromeavecung.dddsample.technicalinfrastructure.tests.bookscucumbertests.books.repositories.NonRandomIdentifierForCucumber;
import fr.geromeavecung.dddsample.technicalinfrastructure.tests.bookscucumbertests.shared.SharedState;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.books.Author;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.books.Book;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.books.BooksService;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.books.Title;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core.Identifier;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import fr.geromeavecung.dddsample.businessdomain.usecases.booksusecases.BooksOrchestrationService;
import org.springframework.beans.factory.annotation.Autowired;
import fr.geromeavecung.dddsample.businessdomain.usecases.booksusecases.BookCreationForm;
import fr.geromeavecung.dddsample.businessdomain.usecases.booksusecases.BookSummary;
import fr.geromeavecung.dddsample.businessdomain.usecases.booksusecases.BookSummaryTable;
import fr.geromeavecung.dddsample.businessdomain.usecases.booksusecases.BooksPresentationService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class BooksSteps {

    // TODO split in common steps and feature steps

    private final BooksForCucumber booksForCucumber;

    private final BooksPresentationService booksPresentationService;

    private final SharedState sharedState;

    @Autowired
    public BooksSteps(BooksForCucumber booksForCucumber, SharedState sharedState, NonRandomIdentifierForCucumber nonRandomIdentifierForCucumber) {
        this.booksForCucumber = booksForCucumber;
        this.sharedState = sharedState;
        booksPresentationService = new BooksPresentationService(new BooksOrchestrationService(new BooksService(booksForCucumber)), nonRandomIdentifierForCucumber);
    }

    @When("the user tries to add a book")
    public void the_user_adds_a_book(DataTable table) {
        sharedState.setActualException(null);
        try {
            for (Map<String, String> columns : table.<String, String>asMaps(String.class, String.class)) {
                BookCreationForm bookCreationForm = new BookCreationForm();
                bookCreationForm.setTitle(columns.get("title"));
                bookCreationForm.setAuthor(columns.get("author"));
                bookCreationForm.setType(Book.Type.valueOf(columns.get("type")));
                booksPresentationService.createBook(bookCreationForm);
            }
        } catch (Exception exception) {
            sharedState.setActualException(exception);
        }
    }

    @Then("the book is added")
    public void theBookIsAdded(DataTable table) {
        sharedState.assertThatNoExceptionIsThrown();
        try {
            for (Map<String, String> columns : table.<String, String>asMaps(String.class, String.class)) {
                Identifier identifier = Identifier.from(columns.get("identifier"));
                // FIXME .get()
                assertThat(booksForCucumber.read(identifier).get()).usingRecursiveComparison().isEqualTo(Book.create(identifier, Title.create(columns.get("title")), Author.create(columns.get("author")), Book.Type.valueOf(columns.get("type"))));
            }
        } catch (Exception exception) {
            sharedState.setActualException(exception);
        }
    }


    @Given("a library with books")
    public void aLibraryWithBooks(DataTable table) {
        try {
            for (Map<String, String> columns : table.<String, String>asMaps(String.class, String.class)) {
                Book book = new Book(Identifier.from(columns.get("identifier")), Title.create(columns.get("title")), Author.create(columns.get("author")), Book.Type.valueOf(columns.get("type")));
                booksForCucumber.save(book);
            }
        } catch (Exception exception) {
            sharedState.setActualException(exception);
        }
    }

    private BookSummaryTable bookSummaryTable;

    @When("the user tries to display all books")
    public void theUserTriesToDisplayAllBooks() {
        sharedState.setActualException(null);
        try {
            bookSummaryTable = booksPresentationService.displayBooks();
        } catch (Exception exception) {
            sharedState.setActualException(exception);
        }
    }

    @Then("the books are displayed")
    public void theBooksAreDisplayed(DataTable table) {
        sharedState.assertThatNoExceptionIsThrown();
        List<BookSummary> bookSummaries = new ArrayList<>();
        for (Map<String, String> columns : table.<String, String>asMaps(String.class, String.class)) {
            bookSummaries.add(new BookSummary(new Book(Identifier.from(columns.get("identifier")), Title.create(columns.get("title")), Author.create(columns.get("author")), Book.Type.valueOf(columns.get("type")))));
        }
        BookSummaryTable expected = new BookSummaryTable(bookSummaries);
        assertThat(bookSummaryTable.getBookSummaries().size()).isEqualTo(expected.getBookSummaries().size());
        List<BookSummary> actualBookSummaries = new ArrayList<>(bookSummaryTable.getBookSummaries());
        List<BookSummary> expectedBookSummaries = new ArrayList<>(expected.getBookSummaries());
        for (int i = 0; i < actualBookSummaries.size(); i++) {
            assertThat(actualBookSummaries.get(i)).usingRecursiveComparison().isEqualTo(expectedBookSummaries.get(i));
        }
    }


}
