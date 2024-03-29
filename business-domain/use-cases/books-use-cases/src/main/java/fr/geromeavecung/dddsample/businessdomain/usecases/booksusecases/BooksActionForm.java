package fr.geromeavecung.dddsample.businessdomain.usecases.booksusecases;

import java.util.HashSet;
import java.util.Set;

public class BooksActionForm {

    private Set<String> actions = new HashSet<>();

    public Set<String> getActions() {
        return actions;
    }

    public void setActions(Set<String> actions) {
        this.actions = actions;
    }

    @Override
    public String toString() {
        return "BooksActionForm{" +
                "actions=" + actions +
                '}';
    }
}
