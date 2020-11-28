package fr.geromeavecung.dddsample.infrastructure.persistenceinmemory;

import org.springframework.data.repository.CrudRepository;

public interface BooksJpaRepository extends CrudRepository<BookJPA, String> {

    }