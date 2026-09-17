package com.joser.topologyinventory.framework.adapters.input.generic;

import com.joser.topologyinventory.application.usecases.NetworkManagementUseCase;
import com.joser.topologyinventory.application.usecases.SwitchManagementUseCase;
import com.joser.topologyinventory.domain.entity.Switch;
import com.joser.topologyinventory.domain.vo.Id;
import com.joser.topologyinventory.domain.vo.Network;
public class NetworkManagementGenericAdapter {

    private SwitchManagementUseCase switchManagementUseCase;
    private NetworkManagementUseCase networkManagementUseCase;

    public NetworkManagementGenericAdapter(SwitchManagementUseCase switchManagementUseCase, NetworkManagementUseCase networkManagementUseCase){
        this.switchManagementUseCase = switchManagementUseCase;
        this.networkManagementUseCase = networkManagementUseCase;
    }

    /**
     * POST /network/add
     * */
    public Switch addNetworkToSwitch(Network network, Id switchId) {
        Switch networkSwitch = switchManagementUseCase.retrieveSwitch(switchId);
        return networkManagementUseCase.addNetworkToSwitch(network, networkSwitch);
    }

    /**
     * POST /network/remove
     * */
    public Switch removeNetworkFromSwitch(String networkName, Id switchId) {
        Switch networkSwitch = switchManagementUseCase.retrieveSwitch(switchId);
        return networkManagementUseCase.removeNetworkFromSwitch(networkName, networkSwitch);
    }
}
