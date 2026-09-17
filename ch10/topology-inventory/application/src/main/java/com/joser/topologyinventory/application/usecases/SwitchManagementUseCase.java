package com.joser.topologyinventory.application.usecases;

import com.joser.topologyinventory.application.ports.output.SwitchManagementOutputPort;
import com.joser.topologyinventory.domain.entity.EdgeRouter;
import com.joser.topologyinventory.domain.entity.Switch;
import com.joser.topologyinventory.domain.vo.IP;
import com.joser.topologyinventory.domain.vo.Id;
import com.joser.topologyinventory.domain.vo.Location;
import com.joser.topologyinventory.domain.vo.Model;
import com.joser.topologyinventory.domain.vo.SwitchType;
import com.joser.topologyinventory.domain.vo.Vendor;

public interface SwitchManagementUseCase {

    public void setOutputPort(SwitchManagementOutputPort switchManagementOutputPort);

    Switch createSwitch(
            Vendor vendor,
            Model model,
            IP ip,
            Location location,
            SwitchType switchType
    );

    Switch retrieveSwitch(Id id);

    EdgeRouter addSwitchToEdgeRouter(Switch networkSwitch, EdgeRouter edgeRouter);

    EdgeRouter removeSwitchFromEdgeRouter(Switch networkSwitch, EdgeRouter edgeRouter);
}
