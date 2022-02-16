package fr.geromeavecung.exposition.presentation;

import fr.geromeavecung.businessdomain.shared.BusinessException;

import java.util.Set;

public class CantDoTwoActionsAtOnce extends BusinessException {

    public CantDoTwoActionsAtOnce(Set<String> idsAction1, Set<String> idsAction2) {
        super(idsAction1.toString(), idsAction2.toString());
    }

}
