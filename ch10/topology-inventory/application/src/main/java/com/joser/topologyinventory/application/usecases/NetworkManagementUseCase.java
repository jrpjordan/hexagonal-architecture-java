package com.joser.topologyinventory.application.usecases;

import com.joser.topologyinventory.application.ports.output.RouterManagementOutputPort;
import com.joser.topologyinventory.domain.entity.Switch;
import com.joser.topologyinventory.domain.vo.IP;
import com.joser.topologyinventory.domain.vo.Network;

public interface NetworkManagementUseCase {

    void setOutputPort(RouterManagementOutputPort routerManagementOutputPort);

    Network createNetwork(IP networkAddress, String networkName,
                        int networkCidr);
                    
    Switch addNetworkToSwitch(Network network, Switch networkSwitch);

    Switch removeNetworkFromSwitch(String network, Switch networkSwitch);
    
}
