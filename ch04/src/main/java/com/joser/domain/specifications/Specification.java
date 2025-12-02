package com.joser.domain.specifications;

public sealed interface Specification<T> permits AbstractSpecification {
    boolean isSatisfiedBy(T t);
    Specification<T> and(Specification<T> specification);
}
