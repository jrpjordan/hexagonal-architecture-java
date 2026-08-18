package com.joser.topologyinventory.application.ports.input;

import com.joser.topologyinventory.application.usecases.SwitchManagementUseCase;
import com.joser.topologyinventory.domain.entity.EdgeRouter;
import com.joser.topologyinventory.domain.entity.Switch;
import com.joser.topologyinventory.domain.vo.IP;
import com.joser.topologyinventory.domain.vo.Id;
import com.joser.topologyinventory.domain.vo.Location;
import com.joser.topologyinventory.domain.vo.Model;
import com.joser.topologyinventory.domain.vo.SwitchType;
import com.joser.topologyinventory.domain.vo.Vendor;

public class SwitchManagementInputPort implements SwitchManagementUseCase {

    @Override
    public Switch createSwitch(Vendor vendor, Model model, IP ip, Location location, SwitchType switchType) {
        return Switch.builder().
        id(Id.withoutId()).
        vendor(vendor).
        model(model).
        ip(ip).
        location(location).
        switchType(switchType).
        build();
    }

    @Override
    public EdgeRouter addSwitchToEdgeRouter(Switch networkSwitch, EdgeRouter edgeRouter) {
        edgeRouter.addSwitch(networkSwitch);
        return edgeRouter;
    }

    @Override
    public EdgeRouter removeSwitchFromEdgeRouter(Switch networkSwitch, EdgeRouter edgeRouter) {
        edgeRouter.removeSwitch(networkSwitch);
        return edgeRouter;
    }
    
}
