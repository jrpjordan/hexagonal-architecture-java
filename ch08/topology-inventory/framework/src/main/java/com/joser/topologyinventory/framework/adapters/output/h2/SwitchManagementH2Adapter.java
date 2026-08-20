package com.joser.topologyinventory.framework.adapters.output.h2;

import com.joser.topologyinventory.application.ports.output.SwitchManagementOutputPort;
import com.joser.topologyinventory.domain.entity.Switch;
import com.joser.topologyinventory.domain.vo.Id;
import com.joser.topologyinventory.framework.adapters.output.h2.data.SwitchData;
import com.joser.topologyinventory.framework.adapters.output.h2.mappers.RouterH2Mapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;

public class SwitchManagementH2Adapter implements SwitchManagementOutputPort {

    private static SwitchManagementH2Adapter INSTANCE;

    @PersistenceContext
    private EntityManager em;

    private SwitchManagementH2Adapter() {
        setUpH2Database();
    }

    @Override
    public Switch retrieveSwitch(Id id) {
        var switchData = em.getReference(SwitchData.class, id.getUuid());
        return RouterH2Mapper.switchDataToDomain(switchData);
    }

    private void setUpH2Database() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("inventory");
        this.em = emf.createEntityManager();
    }

    public static SwitchManagementH2Adapter getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SwitchManagementH2Adapter();
        }
        return INSTANCE;
    }
}
