package fr.geromeavecung.dddsample.technicalinfrastructure.persistence.h2;

import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.authors.Author;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core.FirstName;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core.Identifier;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core.LastName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;
import java.util.Optional;

@Entity
@Table(name = "Authors")
class AuthorJPA {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorJPA.class);

    @Id
    private String identifier;

    private String firstName;

    private String lastName;

    private AuthorJPA() {
        // for JPA
    }

    AuthorJPA(Author author) {
        this.identifier = author.getIdentifier().toString();
        this.firstName = author.getFirstName().toString();
        this.lastName = author.getLastName().toString();
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AuthorJPA bookJPA = (AuthorJPA) o;
        return identifier.equals(bookJPA.identifier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier);
    }

    Optional<Author> toDomain() {
        try {
            return Optional.of(Author.read(Identifier.from(identifier), new FirstName(firstName), new LastName(lastName)));
        } catch (Exception exception) {
            LOGGER.warn("Error while mapping database to domain", exception);
            return Optional.empty();
        }
    }

}
