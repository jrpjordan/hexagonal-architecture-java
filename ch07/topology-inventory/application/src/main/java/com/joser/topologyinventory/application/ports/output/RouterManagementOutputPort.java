package com.joser.topologyinventory.application.ports.output;

import com.joser.topologyinventory.domain.entity.Router;
import com.joser.topologyinventory.domain.vo.Id;

public interface RouterManagementOutputPort {
    
    Router retrieveRouter(Id id);

    Router persistRouter(Router router);
}
