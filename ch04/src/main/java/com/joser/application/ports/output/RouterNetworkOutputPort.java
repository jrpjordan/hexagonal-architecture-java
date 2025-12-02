package com.joser.application.ports.output;

import com.joser.domain.entity.Router;
import com.joser.domain.valueobject.RouterId;

public interface RouterNetworkOutputPort {

    Router fetchRouterById(RouterId routerId);
    boolean persistRouter(Router router);
}
