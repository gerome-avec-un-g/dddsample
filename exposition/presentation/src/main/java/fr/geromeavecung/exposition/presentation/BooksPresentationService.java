package fr.geromeavecung.exposition.presentation;

import fr.geromeavecung.businessdomain.books.Author;
import fr.geromeavecung.businessdomain.books.Book;
import fr.geromeavecung.businessdomain.books.Title;
import fr.geromeavecung.businessdomain.shared.BusinessException;
import fr.geromeavecung.exposition.orchestration.BooksOrchestrationService;

import java.util.Set;
import java.util.stream.Collectors;

public class BooksPresentationService {

    private final BooksOrchestrationService booksOrchestrationService;

    public BooksPresentationService(BooksOrchestrationService booksOrchestrationService) {
        this.booksOrchestrationService = booksOrchestrationService;
    }

    public void createBook(CreateBookRequest createBookRequest) {
        try {
            Title title = Title.create(createBookRequest.getTitle());
            Author author = Author.create(createBookRequest.getAuthor());
            Book book = Book.create(title, author);
            booksOrchestrationService.add(book);
        } catch (BusinessException businessException) {
            throw new BusinessExceptionResponse(businessException.getClass().getSimpleName(), businessException.getParameters());
        }
    }

    public Set<BookSummary> displayBooks() {
        return booksOrchestrationService.displayBooks().stream().map(BookSummary::new).collect(Collectors.toSet());
    }

}
