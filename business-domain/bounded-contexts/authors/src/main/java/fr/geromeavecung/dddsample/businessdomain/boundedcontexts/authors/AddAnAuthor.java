package fr.geromeavecung.dddsample.businessdomain.boundedcontexts.authors;

import java.util.Optional;

public class AddAnAuthor {

    private final Authors authors;

    public AddAnAuthor(Authors authors) {
        this.authors = authors;
    }

    public void execute(Author newAuthor) {
        Optional<Author> sameAuthor = authors.readAll().stream().filter(existingAuthor -> existingAuthor.getFirstName().equals(newAuthor.getFirstName()) && existingAuthor.getLastName().equals(newAuthor.getLastName())).findFirst();
        if(sameAuthor.isPresent()) {
            throw new AuthorAlreadyExists(sameAuthor.get());
        }
        authors.save(newAuthor);
    }

}
