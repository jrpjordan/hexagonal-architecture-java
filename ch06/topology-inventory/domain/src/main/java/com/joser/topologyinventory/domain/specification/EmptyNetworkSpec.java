package com.joser.topologyinventory.domain.specification;

import com.joser.topologyinventory.domain.entity.Switch;
import com.joser.topologyinventory.domain.exception.GenericSpecificationException;

public final class EmptyNetworkSpec extends AbstractSpecification<Switch> {
    @Override
    public boolean isSatisfiedBy(Switch switchNetwork) {
        return switchNetwork.getSwitchNetworks() == null
                || switchNetwork.getSwitchNetworks().isEmpty();
    }

    @Override
    public void check(Switch aSwitch) throws GenericSpecificationException {
        if(!isSatisfiedBy(aSwitch)) {
            throw new GenericSpecificationException("It is not possible to remove a switch with networks attached to it");
        }
    }
}
