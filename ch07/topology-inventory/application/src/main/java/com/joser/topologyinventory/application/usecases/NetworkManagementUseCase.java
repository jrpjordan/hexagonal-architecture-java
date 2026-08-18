package com.joser.topologyinventory.application.usecases;

import com.joser.topologyinventory.domain.entity.Switch;
import com.joser.topologyinventory.domain.vo.IP;
import com.joser.topologyinventory.domain.vo.Network;

public interface NetworkManagementUseCase {

    Network creaNetwork(IP networkAddress, String networkName,
                        int netwokrCidr);
                    
    Switch addNetworkToSwitch(Network network, Switch networkSwitch);

    Switch removeNetworkFromSwitch(Network network, Switch networkSwitch);
    
}
