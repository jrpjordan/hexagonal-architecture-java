package com.joser.framework.adapters.output.file;

import com.joser.application.ports.output.RouterNetworkOutputPort;
import com.joser.domain.entity.Router;
import com.joser.domain.entity.Switch;
import com.joser.domain.valueobject.*;

import java.util.ArrayList;
import java.util.List;

public class RouterNetworkFileAdapter implements RouterNetworkOutputPort {

    private static RouterNetworkFileAdapter instance;
    private List<Router> routers = new ArrayList<>();

    private RouterNetworkFileAdapter() {
        createSampleRouter();
    }

    public static RouterNetworkFileAdapter getInstance() {
        if (instance == null) {
            instance = new RouterNetworkFileAdapter();
        }
        return instance;
    }

    @Override
    public Router fetchRouterById(RouterId routerId) {
        return routers.stream()
                .filter(r -> r.getRouterId().equals(routerId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean persistRouter(Router router) {
        return this.routers.add(router);
    }

    private void createSampleRouter() {
        var routerId = RouterId.withUUID("ca23800e-9b5a-11eb-a8b3-0242ac130003");
        var network = new Network(new IP("10.0.0.0"),"HR", 8);
        var networkSwitch = new Switch(SwitchType.LAYER3, SwitchId.withoutId(), List.of(network), new IP("9.0.0.9"));
        var router = new Router(RouterType.EDGE, routerId, networkSwitch);
        routers.add(router);
    }
}
