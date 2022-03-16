package fr.geromeavecung.dddsample.technicalinfrastructure.persistence.h2;

import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core.Identifier;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.books.Author;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.books.Book;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.books.Title;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;
import java.util.Optional;

@Entity
@Table(name = "Books")
class BookJPA {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookJPA.class);

    @Id
    private String identifier;

    private String title;

    private String author;

    private String type;

    private BookJPA() {
        /*
         * for JPA
         */
    }

    BookJPA(Book book) {
        this.identifier = book.getIdentifier().toString();
        this.title = book.getTitle().getValue();
        this.author = book.getAuthor().getValue();
        this.type = book.getType().name();
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BookJPA bookJPA = (BookJPA) o;
        return identifier.equals(bookJPA.identifier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier);
    }

    Optional<Book> toDomain() {
        try {
            return Optional.of(Book.create(Identifier.from(identifier), Title.create(title), Author.create(author), Book.Type.valueOf(type)));
        } catch (Exception exception) {
            LOGGER.warn("Error while mapping database to domain", exception);
            return Optional.empty();
        }
    }

}
