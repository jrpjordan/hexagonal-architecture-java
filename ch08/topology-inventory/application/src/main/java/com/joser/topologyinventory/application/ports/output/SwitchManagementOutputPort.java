package com.joser.topologyinventory.application.ports.output;

import com.joser.topologyinventory.domain.entity.Switch;
import com.joser.topologyinventory.domain.vo.Id;

public interface SwitchManagementOutputPort {
    Switch retrieveSwitch(Id id);
}
