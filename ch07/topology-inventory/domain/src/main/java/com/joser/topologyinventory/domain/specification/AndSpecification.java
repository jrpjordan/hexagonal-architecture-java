package com.joser.topologyinventory.domain.specification;

import com.joser.topologyinventory.domain.exception.GenericSpecificationException;

public final class AndSpecification<T> extends AbstractSpecification<T> {

    private final Specification<T> spec1;
    private final Specification<T> spec2;

    public AndSpecification(Specification<T> spec1, Specification<T> spec2) {
        this.spec1 = spec1;
        this.spec2 = spec2;
    }

    @Override
    public boolean isSatisfiedBy(T t) {
        return false;
    }

    @Override
    public void check(T t) throws GenericSpecificationException {

    }

}
