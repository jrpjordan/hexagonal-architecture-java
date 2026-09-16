package com.joser.topologyinventory.framework.adapters.input.generic;

import com.joser.topologyinventory.application.ports.input.NetworkManagementInputPort;
import com.joser.topologyinventory.application.ports.input.SwitchManagementInputPort;
import com.joser.topologyinventory.application.usecases.NetworkManagementUseCase;
import com.joser.topologyinventory.application.usecases.SwitchManagementUseCase;
import com.joser.topologyinventory.domain.entity.Switch;
import com.joser.topologyinventory.domain.vo.Id;
import com.joser.topologyinventory.domain.vo.Network;
import com.joser.topologyinventory.framework.adapters.output.h2.RouterManagementH2Adapter;
import com.joser.topologyinventory.framework.adapters.output.h2.SwitchManagementH2Adapter;

public class NetworkManagementGenericAdapter {

    private SwitchManagementUseCase switchManagementUseCase;
    private NetworkManagementUseCase networkManagementUseCase;

    public NetworkManagementGenericAdapter(){
        setPorts();
    }

    private void setPorts(){
        this.switchManagementUseCase = new SwitchManagementInputPort(SwitchManagementH2Adapter.getInstance());
        this.networkManagementUseCase = new NetworkManagementInputPort(RouterManagementH2Adapter.getInstance());
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
