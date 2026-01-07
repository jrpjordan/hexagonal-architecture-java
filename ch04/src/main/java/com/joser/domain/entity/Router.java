package com.joser.domain.entity;

import com.joser.domain.valueobject.IP;
import com.joser.domain.valueobject.Network;
import com.joser.domain.valueobject.RouterId;
import com.joser.domain.valueobject.RouterType;

import java.util.List;
import java.util.function.Predicate;

public class Router {

    private RouterType type;
    private RouterId id;
    private Switch networkSwitch;

    public Router(RouterType routerType, RouterId routerId, Switch networkSwitch) {
        this.type = routerType;
        this.id = routerId;
        this.networkSwitch = networkSwitch;
    }

    public Router(RouterType routerType, RouterId routerId) {
        this.type = routerType;
        this.id = routerId;
    }

    public Router() {}

    public static Predicate<Router> filterRouterByType(RouterType routerType) {
        return routerType.equals(RouterType.CORE)
                ? Router.isCore()
                : Router.isEdge();
    }

    private static Predicate<Router> isCore() {
        return r -> r.getType() == RouterType.CORE;
    }

    private static Predicate<Router> isEdge() {
        return r -> r.getType() == RouterType.EDGE;
    }

    public RouterType getType() {
        return type;
    }

    public void addNetworkToSwitch(Network network) {
        this.networkSwitch = networkSwitch.addNetwork(network, this);
    }

    public Network createNetwork(IP address, String name, int cidr) {
        return new Network(address, name, cidr);
    }

    public List<Network> retrieveNetworks() {
        return networkSwitch.getNetworks();
    }

    public RouterId getId() {
        return id;
    }

    public Switch getNetworkSwitch() {
        return networkSwitch;
    }
}
