package com.joser.framework.adapters.output.h2;

import com.joser.application.ports.output.RouterNetworkOutputPort;
import com.joser.domain.entity.Router;
import com.joser.domain.valueobject.RouterId;
import com.joser.framework.adapters.output.h2.data.RouterData;
import com.joser.framework.adapters.output.h2.mappers.RouterH2Mapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;

public class RouterNetworkH2Adapter implements RouterNetworkOutputPort {

    private static RouterNetworkH2Adapter instance;

    @PersistenceContext
    private EntityManager entityManager;

    private RouterNetworkH2Adapter() {
        setUpH2Database();
    }

    @Override
    public Router fetchRouterById(RouterId routerId) {
        var routerData = entityManager.getReference(RouterData.class, routerId.getId());
        return RouterH2Mapper.toDomain(routerData);
    }

    @Override
    public boolean persistRouter(Router router) {
        var routerData = RouterH2Mapper.toH2(router);
        entityManager.persist(routerData);
        return true;
    }

    private void setUpH2Database() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("inventory");
        EntityManager em = entityManagerFactory.createEntityManager();
        this.entityManager = em;
    }

    public static RouterNetworkH2Adapter getInstance() {
        if (instance == null) {
            instance = new RouterNetworkH2Adapter();
        }
        return instance;
    }
}
