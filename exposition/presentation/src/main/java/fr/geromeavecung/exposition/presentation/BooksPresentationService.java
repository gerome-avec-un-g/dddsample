package fr.geromeavecung.exposition.presentation;

import fr.geromeavecung.businessdomain.books.Author;
import fr.geromeavecung.businessdomain.books.Book;
import fr.geromeavecung.businessdomain.books.Title;
import fr.geromeavecung.businessdomain.shared.BusinessException;

public class BooksPresentationService {

    public void createBook(CreateBookRequest createBookRequest) {
        try {
            Title title = Title.create(createBookRequest.getTitle());
            Author author = Author.create(createBookRequest.getAuthor());
            Book.create(title, author);
        } catch (BusinessException businessException) {
            throw new BusinessExceptionResponse(businessException.getClass().getSimpleName(), businessException.getParameters());
        }
    }

}
