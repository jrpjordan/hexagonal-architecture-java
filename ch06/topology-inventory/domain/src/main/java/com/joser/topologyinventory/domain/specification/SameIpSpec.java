package com.joser.topologyinventory.domain.specification;

import com.joser.topologyinventory.domain.entity.Equipment;
import com.joser.topologyinventory.domain.exception.GenericSpecificationException;

public final class SameIpSpec extends AbstractSpecification<Equipment> {

    private final Equipment equipment;

    public SameIpSpec(Equipment equipment) {
        this.equipment = equipment;
    }

    @Override
    public boolean isSatisfiedBy(Equipment equipment) {
        return !equipment.getIp().equals(equipment.getIp());
    }

    @Override
    public void check(Equipment equipment) throws GenericSpecificationException {
        if(!isSatisfiedBy(equipment))
            throw new GenericSpecificationException("It's not possible to attach routers with the same IP");
    }
}
