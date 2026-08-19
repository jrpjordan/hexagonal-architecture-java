package com.joser.topologyinventory.application.ports.input;

import com.joser.topologyinventory.application.usecases.NetworkManagementUseCase;
import com.joser.topologyinventory.domain.entity.Switch;
import com.joser.topologyinventory.domain.vo.IP;
import com.joser.topologyinventory.domain.vo.Network;

public class NetworkManagementInputPort implements NetworkManagementUseCase {

    @Override
    public Network createNetwork(IP networkAddress, String networkName, int networkCidr) {
        return Network.builder().
                networkAddress(networkAddress).
                networkName(networkName).
                networkCidr(networkCidr).
                build();
    }

    @Override
    public Switch addNetworkToSwitch(Network network, Switch networkSwitch) {
        networkSwitch.addNetworkToSwitch(network);
        return networkSwitch;
    }

    @Override
    public Switch removeNetworkFromSwitch(Network network, Switch networkSwitch) {
        networkSwitch.removeNetworkFromSwitch(network);
        return networkSwitch;
    }
    
}
