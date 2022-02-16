package fr.geromeavecung.dddsample.businessdomain.boundedcontexts.users;

import fr.geromeavecung.businessdomain.shared.Identifier;

import java.util.List;

public interface Users {

    List<User> readAll();

    User read(Identifier identifier);

}
