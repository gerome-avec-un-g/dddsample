package fr.geromeavecung.exposition.presentation;

import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core.Identifiers;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.books.Book;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.books.Title;
import orchestration.BooksOrchestrationService;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BooksPresentationService {

    private final BooksOrchestrationService booksOrchestrationService;

    private final Identifiers identifiers;

    public BooksPresentationService(BooksOrchestrationService booksOrchestrationService, Identifiers identifiers) {
        this.booksOrchestrationService = booksOrchestrationService;
        this.identifiers = identifiers;
    }

    public void createBook(BookCreationForm bookCreationForm) {
        booksOrchestrationService.add(bookCreationForm.toDomain(identifiers.generateNewIdentifier()));
    }

    public BookSummaryTable displayBooks() {
        List<BookSummary> bookSummaries = booksOrchestrationService.displayBooks().stream()
                .sorted(Comparator.comparing(Book::getAuthor).thenComparing(Book::getTitle))
                .map(BookSummary::new).collect(Collectors.toList());
        return new BookSummaryTable(bookSummaries);
    }

    public void booksAction(BooksActionForm booksActionForm) {
        // FIXME no exception in presentation ?
        Set<String> idsAction1 = booksActionForm.getActions().stream().filter(action -> action.contains("action1")).map(action -> action.replace("-action1", "")).collect(Collectors.toSet());
        Set<String> idsAction2 = booksActionForm.getActions().stream().filter(action -> action.contains("action2")).map(action -> action.replace("-action2", "")).collect(Collectors.toSet());
        if (idsAction1.stream().anyMatch(idsAction2::contains)) {
            throw new CantDoTwoActionsAtOnce(idsAction1, idsAction2);
        }
    }

    public BookDetail bookDetail(String id) {
        Title title = Title.create(id);
        return new BookDetail(booksOrchestrationService.bookDetail(title));
    }
}
