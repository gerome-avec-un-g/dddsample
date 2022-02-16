package fr.geromeavecung.exposition.presentation.cucumber.books.repositories;

import fr.geromeavecung.businessdomain.shared.Identifier;
import fr.geromeavecung.businessdomain.shared.Identifiers;
import io.cucumber.spring.CucumberTestContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

@Service
@Scope(CucumberTestContext.SCOPE_CUCUMBER_GLUE)
public class NonRandomIdentifierForCucumber implements Identifiers {

    private final static List<String> UUIDS = Arrays.asList(
            "c9a147a3-4060-49ea-a546-b02c0cf7d98a",
            "6dbf0947-3fd5-47da-90fa-2598b2331bb0",
            "c07c76cb-d2e9-4d1e-92ed-c362bf9f835b",
            "8d8d3d56-58e4-47b4-ac51-184158dfe130",
            "418f797b-f723-4c4c-a34c-616c6897c069",
            "39ea0d85-6cb3-4a9a-970c-2d5125bff1a6",
            "84e6df45-f799-4eac-9ef4-d123a6f677ae",
            "11f9a58b-cdca-4382-8a9c-7829f41624c1",
            "57762050-12b4-4a70-8ae9-9e09c2e86059");

    private final Deque<Identifier> values = new ArrayDeque<>();

    public NonRandomIdentifierForCucumber() {
        UUIDS.forEach(uuid -> values.add(Identifier.from(uuid)));
    }

    @Override
    public Identifier generateNewIdentifier() {
        return values.pop();
    }

}
