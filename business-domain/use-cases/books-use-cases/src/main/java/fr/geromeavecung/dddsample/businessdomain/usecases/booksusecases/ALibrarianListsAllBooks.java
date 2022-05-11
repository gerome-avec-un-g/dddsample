package fr.geromeavecung.dddsample.businessdomain.usecases.booksusecases;

import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.authors.Author;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.authors.ReadAuthors;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.books.Book;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.books.ListAllBooks;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core.Identifier;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.users.User;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ALibrarianListsAllBooks {

    private final ListAllBooks listAllBooks;

    private final ReadAuthors readAuthors;

    public ALibrarianListsAllBooks(ListAllBooks listAllBooks, ReadAuthors readAuthors) {
        this.listAllBooks = listAllBooks;
        this.readAuthors = readAuthors;
    }

    public BookSummaryTable execute(User connectedUser) {
        Set<Book> books = listAllBooks.execute();
        Map<Identifier, Author> authorsByIdentifier = readAuthors.execute().stream().collect(Collectors.toMap(Author::getIdentifier, Function.identity()));
        List<BookSummary> bookSummaries = books.stream()
                .map(book -> new BookSummary(book, authorsByIdentifier.get(book.getAuthorIdentifier())))
                .sorted(Comparator.comparing(BookSummary::getAuthorLastName).thenComparing(BookSummary::getAuthorFirstName).thenComparing(BookSummary::getTitle))
                .collect(Collectors.toList());
        return new BookSummaryTable(bookSummaries);
    }

}
