package com.joser.topologyinventory.domain.specification;

import com.joser.topologyinventory.domain.exception.GenericSpecificationException;

public final class CIDRSpecification extends AbstractSpecification<Integer> {

    final static public int MINIMUM_ALLOWED_CIDR = 8;

    @Override
    public boolean isSatisfiedBy(Integer cidr) {
        return cidr >= MINIMUM_ALLOWED_CIDR;
    }

    @Override
    public void check(Integer cidr) throws GenericSpecificationException {
        if(!isSatisfiedBy(cidr))
            throw new GenericSpecificationException("CIDR must be greater than or equal to " + MINIMUM_ALLOWED_CIDR);
    }
}
