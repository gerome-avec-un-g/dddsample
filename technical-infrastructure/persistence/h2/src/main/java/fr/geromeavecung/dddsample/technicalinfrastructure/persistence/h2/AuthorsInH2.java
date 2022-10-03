package fr.geromeavecung.dddsample.technicalinfrastructure.persistence.h2;

import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.authors.Author;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.authors.Authors;
import fr.geromeavecung.dddsample.businessdomain.boundedcontexts.core.Identifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Repository
public class AuthorsInH2 implements Authors {

    /*
     * with spring-dev-tools we can access H2 at http://localhost:8080/h2-console
     */

    private final AuthorsJpaRepository authorsJpaRepository;

    //FIXME test dto projection

    @Autowired
    public AuthorsInH2(AuthorsJpaRepository authorsJpaRepository) {
        this.authorsJpaRepository = authorsJpaRepository;
    }

    @Override
    public Set<Author> readAll() {
        return StreamSupport.stream(authorsJpaRepository.findAll().spliterator(), false)
                .map(AuthorJPA::toDomain)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());
    }

    @Override
    public Optional<Author> read(Identifier identifier) {
        return Optional.empty();
    }

    @Override
    public void save(Author author) {
        authorsJpaRepository.save(new AuthorJPA(author));
    }

}