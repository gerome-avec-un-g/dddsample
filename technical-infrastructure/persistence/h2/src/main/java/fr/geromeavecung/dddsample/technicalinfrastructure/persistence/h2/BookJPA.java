package fr.geromeavecung.dddsample.technicalinfrastructure.persistence.h2;

import fr.geromeavecung.businessdomain.books.Author;
import fr.geromeavecung.businessdomain.books.Book;
import fr.geromeavecung.businessdomain.books.Title;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Optional;

@Entity
@Table(name = "Books")
class BookJPA {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookJPA.class);

    @Id
    private String title;

    private String author;

    private String type;

    private BookJPA() {
        /*
         * for JPA
         */
    }

    BookJPA(Book book) {
        this.title = book.getTitle().getValue();
        this.author = book.getAuthor().getValue();
        this.type = book.getType().name();
    }

    String getTitle() {
        return title;
    }

    String getAuthor() {
        return author;
    }

    String getType() {
        return type;
    }

    void setTitle(String title) {
        this.title = title;
    }

    void setAuthor(String author) {
        this.author = author;
    }

    void setType(String type) {
        this.type = type;
    }

    Optional<Book> toDomain() {
        try {
            return Optional.of(Book.create(Title.create(title), Author.create(author), Book.Type.valueOf(type)));
        } catch (Exception exception) {
            LOGGER.error("Error while mapping database to domain", exception);
            return Optional.empty();
        }
    }

    @Override
    public String toString() {
        return "BookJPA{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

}
