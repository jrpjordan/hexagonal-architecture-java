package com.joser.topologyinventory.domain.service;

import com.joser.topologyinventory.domain.entity.Equipment;
import com.joser.topologyinventory.domain.entity.Router;
import com.joser.topologyinventory.domain.vo.Id;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class RouterService {

    public static List<Router> filterAndRetrieveRouter(List<Router> routers,
                                                       Predicate<Equipment> routerPredicate) {
        return routers
                .stream()
                .filter(routerPredicate)
                .collect(Collectors.toList());
    }

    public static Router findById(Map<Id, Router> routers, Id id) {
        return routers.get(id);
    }
}
