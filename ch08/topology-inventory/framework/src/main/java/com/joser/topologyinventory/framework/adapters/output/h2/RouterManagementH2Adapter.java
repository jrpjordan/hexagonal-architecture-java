package com.joser.topologyinventory.framework.adapters.output.h2;

import com.joser.topologyinventory.application.ports.output.RouterManagementOutputPort;
import com.joser.topologyinventory.domain.entity.Router;
import com.joser.topologyinventory.domain.vo.Id;
import com.joser.topologyinventory.framework.adapters.output.h2.data.RouterData;
import com.joser.topologyinventory.framework.adapters.output.h2.mappers.RouterH2Mapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;


public class RouterManagementH2Adapter implements RouterManagementOutputPort {

    private static RouterManagementH2Adapter instance;

    @PersistenceContext
    private EntityManager em;

    private RouterManagementH2Adapter() {
        setUpH2Database();
    }


    @Override
    public Router retrieveRouter(Id id) {
        var routerData = em.getReference(RouterData.class, id.getUuid());
        return RouterH2Mapper.routerDataToDomain(routerData);
    }

    @Override
    public Router removeRouter(Id id) {
        var routerData = em.getReference(RouterData.class, id.getUuid());
        em.remove(routerData);
        return null;
    }

    @Override
    public Router persistRouter(Router router) {
        var routerData = RouterH2Mapper.routerDomainToData(router);
        em.persist(routerData);
        return router;
    }

    private void setUpH2Database() {
        EntityManagerFactory emf = Persistence.
                createEntityManagerFactory("inventory");
        this.em = emf.createEntityManager();
    }

    public static RouterManagementH2Adapter getInstance() {
        if (instance == null) {
            instance = new RouterManagementH2Adapter();
        }
        return instance;
    }
}
