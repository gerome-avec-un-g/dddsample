package fr.geromeavecung.exposition.presentation;

import fr.geromeavecung.businessdomain.books.Author;
import fr.geromeavecung.businessdomain.books.Book;
import fr.geromeavecung.businessdomain.books.Title;
import fr.geromeavecung.exposition.orchestration.BooksOrchestrationService;

import java.util.Set;
import java.util.stream.Collectors;

public class BooksPresentationService {

    private final BooksOrchestrationService booksOrchestrationService;

    public BooksPresentationService(BooksOrchestrationService booksOrchestrationService) {
        this.booksOrchestrationService = booksOrchestrationService;
    }

    public void createBook(CreateBookForm createBookForm) {
        Title title = Title.create(createBookForm.getTitle());
        Author author = Author.create(createBookForm.getAuthor());
        Book book = Book.create(title, author, createBookForm.getType());
        booksOrchestrationService.add(book);
    }

    public Set<BookSummary> displayBooks() {
        return booksOrchestrationService.displayBooks().stream().map(BookSummary::new).collect(Collectors.toSet());
    }

    public void booksAction(BooksActionForm booksActionForm) {
        System.out.println(booksActionForm.toString());
        Set<String> idsAction1 = booksActionForm.getActions().stream().filter(action -> action.contains("action1")).map(action -> action.replace("-action1", "")).collect(Collectors.toSet());
        Set<String> idsAction2 = booksActionForm.getActions().stream().filter(action -> action.contains("action2")).map(action -> action.replace("-action2", "")).collect(Collectors.toSet());
        if (idsAction1.stream().anyMatch(idsAction2::contains)) {
            throw new CantDoTwoActionsAtOnce(idsAction1, idsAction2);
        }

    }
}
