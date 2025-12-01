package com.joser.domain.entity;

import com.joser.domain.valueobject.IP;
import com.joser.domain.valueobject.Network;
import com.joser.domain.valueobject.RouterId;
import com.joser.domain.valueobject.RouterType;

import java.util.List;
import java.util.function.Predicate;

public class Router {

    private final RouterType routerType;
    private final RouterId routerId;
    private Switch networkSwitch;

    public Router(RouterType routerType, RouterId routerId, Switch networkSwitch) {
        this.routerType = routerType;
        this.routerId = routerId;
        this.networkSwitch = networkSwitch;
    }

    public Router(RouterType routerType, RouterId routerId) {
        this.routerType = routerType;
        this.routerId = routerId;
    }

    public static Predicate<Router> filterRouterByType(RouterType routerType) {
        return routerType.equals(RouterType.CORE)
                ? Router.isCore()
                : Router.isEdge();
    }

    private static Predicate<Router> isCore() {
        return r -> r.getRouterType() == RouterType.CORE;
    }

    private static Predicate<Router> isEdge() {
        return r -> r.getRouterType() == RouterType.EDGE;
    }

    public RouterType getRouterType() {
        return routerType;
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

    public RouterId getRouterId() {
        return routerId;
    }
}
