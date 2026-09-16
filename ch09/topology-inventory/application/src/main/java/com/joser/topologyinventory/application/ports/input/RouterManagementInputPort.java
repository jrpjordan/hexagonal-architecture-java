package com.joser.topologyinventory.application.ports.input;

import com.joser.topologyinventory.application.ports.output.RouterManagementOutputPort;
import com.joser.topologyinventory.application.usecases.RouterManagementUseCase;
import com.joser.topologyinventory.domain.entity.CoreRouter;
import com.joser.topologyinventory.domain.entity.Router;
import com.joser.topologyinventory.domain.entity.factory.RouterFactory;
import com.joser.topologyinventory.domain.vo.IP;
import com.joser.topologyinventory.domain.vo.Id;
import com.joser.topologyinventory.domain.vo.Location;
import com.joser.topologyinventory.domain.vo.Model;
import com.joser.topologyinventory.domain.vo.RouterType;
import com.joser.topologyinventory.domain.vo.Vendor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class RouterManagementInputPort implements RouterManagementUseCase {

    RouterManagementOutputPort routerManagementOutputPort;

    @Override
    public void setOutputPort(RouterManagementOutputPort routerManagementOutputPort) {
        this.routerManagementOutputPort = routerManagementOutputPort;
    }

    @Override
    public Router createRouter(Id id,
                               Vendor vendor,
                               Model model,
                               IP ip,
                               Location location,
                               RouterType routerType) {
        return RouterFactory.getRouter(null,
                vendor,
                model,
                ip,
                location,
                routerType
        );
    }

    @Override
    public Router removeRouter(Id id) {
        return routerManagementOutputPort.removeRouter(id);
    }

    @Override
    public Router retrieveRouter(Id id) {
        return routerManagementOutputPort.retrieveRouter(id);
    }

    @Override
    public Router persistRouter(Router router) {
        return routerManagementOutputPort.persistRouter(router);
    }

    @Override
    public CoreRouter addRouterToCoreRouter(Router router, CoreRouter coreRouter) {
        var addedRouter = coreRouter.addRouter(router);
        return (CoreRouter) persistRouter(addedRouter);
    }

    @Override
    public Router removeRouterFromCoreRouter(Router router, CoreRouter coreRouter) {
        var removedRouter = coreRouter.removeRouter(router);
        persistRouter(coreRouter);
        return removedRouter;
    }
}