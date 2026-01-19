package com.joser.application.ports.input;

import com.joser.application.ports.output.NotifyEventOutputPort;
import com.joser.application.ports.output.RouterNetworkOutputPort;
import com.joser.application.usecases.RouterNetworkUseCase;
import com.joser.domain.entity.Router;
import com.joser.domain.service.NetworkOperation;
import com.joser.domain.valueobject.Network;
import com.joser.domain.valueobject.RouterId;

public class RouterNetworkInputPort implements RouterNetworkUseCase {

    private final RouterNetworkOutputPort routerNetworkOutputPort;

    private NotifyEventOutputPort notifyEventOutputPort;

    public RouterNetworkInputPort(RouterNetworkOutputPort routerNetworkOutputPort,
                                  NotifyEventOutputPort notifyEventOutputPort){
        this.routerNetworkOutputPort = routerNetworkOutputPort;
        this.notifyEventOutputPort = notifyEventOutputPort;
    }

    public RouterNetworkInputPort(RouterNetworkOutputPort routerNetworkOutputPort){
        this.routerNetworkOutputPort = routerNetworkOutputPort;
    }

    @Override
    public Router addNetworkToRouter(RouterId routerId, Network network) {
        var router = fetchRouter(routerId);
        notifyEventOutputPort.sendEvent("Adding "+network.name()+" network to router "+router.getId().getId());
        return createNetwork(router, network);
    }

    @Override
    public Router getRouter(RouterId routerId) {
        notifyEventOutputPort.sendEvent("Retrieving router ID "+routerId.getId());
        return fetchRouter(routerId);
    }

    private Router fetchRouter(RouterId routerId) {
        return routerNetworkOutputPort.fetchRouterById(routerId);
    }

    private Router createNetwork(Router router, Network network) {
        var newRouter = NetworkOperation.createNewNetwork(router, network);
        return persistNetwork(router) ? newRouter : router;
    }

    private boolean persistNetwork(Router router) {
        return routerNetworkOutputPort.persistRouter(router);
    }

}
