package fr.geromeavecung.exposition.presentation;

import fr.geromeavecung.businessdomain.books.Book;
import fr.geromeavecung.businessdomain.shared.BusinessException;

public class BooksPresentationService {

    public void createBook(CreateBookRequest createBookRequest) {
        try {
            Book.create(createBookRequest.getTitle());
        } catch (BusinessException businessException) {
            throw new BusinessExceptionResponse(businessException.getClass().getSimpleName(), businessException.getParameters());
        }
    }

}
