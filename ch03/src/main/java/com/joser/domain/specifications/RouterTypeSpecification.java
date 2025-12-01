package com.joser.domain.specifications;

import com.joser.domain.entity.Router;
import com.joser.domain.valueobject.RouterType;

public final class RouterTypeSpecification extends AbstractSpecification<Router> {

    @Override
    public boolean isSatisfiedBy(Router router) {
        return router.getRouterType().equals(RouterType.CORE)
                || router.getRouterType().equals(RouterType.EDGE);
    }
}
