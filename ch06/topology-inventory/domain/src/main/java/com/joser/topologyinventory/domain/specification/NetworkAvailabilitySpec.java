package com.joser.topologyinventory.domain.specification;

import com.joser.topologyinventory.domain.entity.Equipment;
import com.joser.topologyinventory.domain.entity.Switch;
import com.joser.topologyinventory.domain.exception.GenericSpecificationException;
import com.joser.topologyinventory.domain.vo.IP;
import com.joser.topologyinventory.domain.vo.Network;

public final class NetworkAvailabilitySpec extends AbstractSpecification<Equipment> {

    private final IP address;
    private final String name;
    private final int cidr;

    public NetworkAvailabilitySpec(Network network) {
        this.address = network.getNetworkAddress();
        this.name = network.getNetworkName();
        this.cidr = network.getNetworkCidr();
    }

    @Override
    public boolean isSatisfiedBy(Equipment switchNetworks) {
        return switchNetworks != null && isNetwrorkAvailable(switchNetworks);
    }

    @Override
    public void check(Equipment equipment) throws GenericSpecificationException {
        if(!isSatisfiedBy(equipment))
            throw new GenericSpecificationException("This network already exists");
    }

    private boolean isNetwrorkAvailable(Equipment switchNetworks) {
        var availability = true;
        for (Network network : ((Switch)switchNetworks).getSwitchNetworks()) {
            if(network.getNetworkAddress().equals(this.address)
                    && network.getNetworkName().equals(this.name)
                    && network.getNetworkCidr() == this.cidr)
                availability = false;
            break;
        }
        return availability;
    }
}
