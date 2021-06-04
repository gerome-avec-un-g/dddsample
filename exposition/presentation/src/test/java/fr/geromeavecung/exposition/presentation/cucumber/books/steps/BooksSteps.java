package fr.geromeavecung.exposition.presentation.cucumber.books.steps;

import fr.geromeavecung.businessdomain.books.Author;
import fr.geromeavecung.businessdomain.books.Book;
import fr.geromeavecung.businessdomain.books.BooksService;
import fr.geromeavecung.businessdomain.books.Title;
import fr.geromeavecung.exposition.orchestration.BooksOrchestrationService;
import fr.geromeavecung.exposition.presentation.BookCreationForm;
import fr.geromeavecung.exposition.presentation.BookSummary;
import fr.geromeavecung.exposition.presentation.BooksPresentationService;
import fr.geromeavecung.exposition.presentation.cucumber.books.repositories.BooksInMemory;
import fr.geromeavecung.exposition.presentation.cucumber.shared.SharedState;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class BooksSteps {

    private final BooksInMemory booksInMemory;

    private final BooksPresentationService booksPresentationService;

    private final SharedState sharedState;

    @Autowired
    public BooksSteps(BooksInMemory booksInMemory, SharedState sharedState) {
        this.booksInMemory = booksInMemory;
        booksPresentationService = new BooksPresentationService(new BooksOrchestrationService(new BooksService(booksInMemory)));
        this.sharedState = sharedState;
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
                assertThat(booksInMemory.findAll()).contains(Book.create(Title.create(columns.get("title")), Author.create(columns.get("author")), Book.Type.valueOf(columns.get("type"))));
            }
        } catch (Exception exception) {
            sharedState.setActualException(exception);
        }
    }


    @Given("a library with books")
    public void aLibraryWithBooks(DataTable table) {
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

    private Set<BookSummary> bookSummaries;

    @When("the user tries to display all books")
    public void theUserTriesToDisplayAllBooks() {
        sharedState.setActualException(null);
        try {
            bookSummaries = booksPresentationService.displayBooks();
        } catch (Exception exception) {
            sharedState.setActualException(exception);
        }
    }

    @Then("the books are displayed")
    public void theBooksAreDisplayed(DataTable table) {
        sharedState.assertThatNoExceptionIsThrown();
        Set<BookSummary> expected = new HashSet<>();
        for (Map<String, String> columns : table.<String, String>asMaps(String.class, String.class)) {
            expected.add(new BookSummary(new Book(Title.create(columns.get("title")), Author.create(columns.get("author")), Book.Type.valueOf(columns.get("type")))));
        }
        assertThat(bookSummaries).isEqualTo(expected);
    }



}
