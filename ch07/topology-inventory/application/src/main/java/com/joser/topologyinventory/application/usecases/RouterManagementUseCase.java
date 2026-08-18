package com.joser.topologyinventory.application.usecases;

import com.joser.topologyinventory.domain.entity.CoreRouter;
import com.joser.topologyinventory.domain.vo.IP;
import com.joser.topologyinventory.domain.vo.Location;
import com.joser.topologyinventory.domain.vo.RouterType;
import com.joser.topologyinventory.domain.vo.Vendor;
import com.joser.topologyinventory.domain.entity.Router;
import com.joser.topologyinventory.domain.vo.Model;
import com.joser.topologyinventory.domain.vo.Id;

public interface RouterManagementUseCase {

    Router createRouter(Vendor vendor, Model model, IP ip,
                        Location location, RouterType routerType);
    
    CoreRouter addRouterToCoreRouter(Router router, CoreRouter coreRouter);

    Router removeRouterFromCoreRouter(Router router, CoreRouter coreRouter);

    Router retrieveRouter(Id id);

    Router persistRouter(Router router);

}
