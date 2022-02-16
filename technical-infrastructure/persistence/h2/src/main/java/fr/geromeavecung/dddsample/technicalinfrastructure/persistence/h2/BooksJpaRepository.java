package fr.geromeavecung.dddsample.technicalinfrastructure.persistence.h2;

import org.springframework.data.repository.CrudRepository;

public interface BooksJpaRepository extends CrudRepository<BookJPA, String> {

}