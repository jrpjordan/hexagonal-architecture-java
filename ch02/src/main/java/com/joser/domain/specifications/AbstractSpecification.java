package com.joser.domain.specifications;

public abstract sealed class AbstractSpecification<T> implements Specification<T>
permits
AndSpecification,
CIDRSpecification,
NetworkAmountSpecification,
NetworkAvailabilitySpecification,
RouterTypeSpecification {

    public abstract boolean isSatisfiedBy(T t);
    public Specification<T> and(Specification<T> specification) {
        return new AndSpecification<T>(this, specification);
    }
}
