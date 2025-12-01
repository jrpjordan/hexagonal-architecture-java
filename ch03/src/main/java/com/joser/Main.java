package com.joser;

import com.joser.domain.valueobject.IP;
import com.joser.domain.valueobject.Network;
import com.joser.framework.adapters.input.stdin.RouterNetworkCliAdapter;
import com.joser.domain.valueobject.RouterId;


public class Main {
    public static void main(String[] args) {
        var cli = new RouterNetworkCliAdapter();
        var routerId = RouterId.withUUID("ca23800e-9b5a-11eb-a8b3-0242ac130003");
        var network = new Network(new IP("20.0.0.0"), "Marketing", 8);
        var router = cli.addNetwork(routerId, network);
        System.out.println(router);
    }
}