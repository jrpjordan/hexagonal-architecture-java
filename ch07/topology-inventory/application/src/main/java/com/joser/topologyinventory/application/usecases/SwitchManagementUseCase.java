package com.joser.topologyinventory.application.usecases;

import com.joser.topologyinventory.domain.entity.EdgeRouter;
import com.joser.topologyinventory.domain.entity.Switch;
import com.joser.topologyinventory.domain.vo.IP;
import com.joser.topologyinventory.domain.vo.Location;
import com.joser.topologyinventory.domain.vo.Model;
import com.joser.topologyinventory.domain.vo.SwitchType;
import com.joser.topologyinventory.domain.vo.Vendor;

public interface SwitchManagementUseCase {
    
    Switch createSwitch(Vendor vendor, Model model, IP ip,
                        Location location, SwitchType switchType);

    EdgeRouter addSwitchToEdgeRouter(Switch networkSwitch, 
                                    EdgeRouter edgeRouter);

    EdgeRouter removeSwitchFromEdgeRouter(Switch networkSwitch,
                                         EdgeRouter edgeRouter);
}
