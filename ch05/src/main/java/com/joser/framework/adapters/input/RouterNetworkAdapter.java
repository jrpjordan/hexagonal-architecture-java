package com.joser.framework.adapters.input;

import com.joser.application.usecases.RouterNetworkUseCase;
import com.joser.domain.entity.Router;
import com.joser.domain.valueobject.IP;
import com.joser.domain.valueobject.Network;
import com.joser.domain.valueobject.RouterId;

import java.util.Map;

public abstract class RouterNetworkAdapter {

    protected Router router;
    protected RouterNetworkUseCase routerNetworkUseCase;

    protected Router addNetworkToRouter(Map<String, String> params) {
        var routerId = RouterId.withUUID(params.get("routerId"));
        var network = new Network(
                IP.fromAddress(params.get("address")),
                params.get("name"),
                Integer.valueOf(params.get("cidr"))
        );
        return routerNetworkUseCase.addNetworkToRouter(routerId, network);
    }

    public Router getRouter(Map<String, String> params) {
        var routerId = RouterId.withUUID(params.get("routerId"));
        return routerNetworkUseCase.getRouter(routerId);
    }

    public abstract Router processRequest(Object requestParams);
}
