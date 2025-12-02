package com.joser.domain.entity;

import com.joser.domain.valueobject.IP;
import com.joser.domain.valueobject.Network;
import com.joser.domain.valueobject.SwitchId;
import com.joser.domain.valueobject.SwitchType;

import java.util.ArrayList;
import java.util.List;

public class Switch {

    private final SwitchType switchType;
    private final SwitchId switchId;
    private final List<Network> networkList;
    private final IP address;

    public Switch (SwitchType switchType, SwitchId switchId,
                   List<Network> networks, IP address) {
        this.switchType = switchType;
        this.switchId = switchId;
        this.networkList = networks;
        this.address = address;
    }

    public Switch addNetwork(Network network, Router router) {
        List<Network> newNetworks = new ArrayList<>(router.retrieveNetworks());
        newNetworks.add(network);
        return new Switch(
                this.switchType,
                this.switchId,
                newNetworks,
                this.address
        );
    }

    public List<Network> getNetworks() {
        return networkList;
    }

    public SwitchId getSwitchId() {
        return switchId;
    }

    public SwitchType getSwitchType() {
        return switchType;
    }

    public IP getAddress() {
        return address;
    }
}
