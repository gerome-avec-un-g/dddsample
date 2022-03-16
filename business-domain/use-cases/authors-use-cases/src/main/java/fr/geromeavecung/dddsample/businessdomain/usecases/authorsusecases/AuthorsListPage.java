package fr.geromeavecung.dddsample.businessdomain.usecases.authorsusecases;

import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.authors.Author;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AuthorsListPage {

    private final Set<Author> authors;

    public AuthorsListPage(Set<Author> authors) {
        this.authors = authors;
    }

    public List<AuthorRow> getAuthorsRow() {
        return authors.stream()
                .map(AuthorRow::new)
                .sorted(Comparator.comparing(AuthorRow::getLastName).thenComparing(AuthorRow::getFirstName))
                .collect(Collectors.toList());
    }

    public InformationMessage getInformationMessage() {
        if (authors.isEmpty()) {
            return InformationMessage.EMPTY_AUTHOR_LIST;
        }
        return InformationMessage.NO_INFORMATION_MESSAGE;
    }

}
