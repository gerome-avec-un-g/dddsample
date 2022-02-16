package fr.geromeavecung.exposition.presentation;

import fr.geromeavecung.businessdomain.shared.BusinessException;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class CantDoTwoActionsAtOnce extends BusinessException {

    private final Set<String> idsAction1;

    private final Set<String> idsAction2;

    public CantDoTwoActionsAtOnce(Set<String> idsAction1, Set<String> idsAction2) {
        super(idsAction1, idsAction2);
        this.idsAction1 = idsAction1;
        this.idsAction2 = idsAction2;
    }

    @Override
    public List<String> getParameters() {
        return Arrays.asList(idsAction1.toString(), idsAction2.toString());
    }
}
