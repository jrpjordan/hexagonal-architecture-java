package com.joser.framework.adapters.output.file.mappers;

import com.joser.domain.entity.Router;
import com.joser.domain.entity.Switch;
import com.joser.domain.valueobject.*;
import com.joser.framework.adapters.output.file.json.*;

import java.util.ArrayList;
import java.util.List;

public class RouterJsonFileMapper {

    public static Router toDoamin(RouterJson routerJson) {
        var routerId = RouterId.withUUID(routerJson.getRouterId().toString());
        var routerType = RouterType.valueOf(routerJson.getRouterType().toString());
        var switchId = SwitchId.withId(routerJson.getNetworkSwitch().getSwitchId().toString());
        var switchType = SwitchType.valueOf(routerJson.getNetworkSwitch().getSwitchType().toString());
        var ip = IP.fromAddress(routerJson.getNetworkSwitch().getIp().getAddress());
        var networks = getNetworksFromJson(routerJson.getNetworkSwitch().getNetworks());
        var networkSwitch = new Switch(
                switchType,
                switchId,
                networks,
                ip
        );
        return new Router(routerType, routerId, networkSwitch);
    }

    public static RouterJson toJson(Router router) {
        var routerId = router.getRouterId().getId();
        var routerTypeJson = RouterTypeJson.valueOf(router.getRouterType().toString());
        var switchIdJson = router.getNetworkSwitch().getSwitchId().getId();
        var switchTypeJson = SwitchTypeJson.valueOf(router.getNetworkSwitch().getSwitchType().toString());
        var ipJson = IPJson.fromAddress(router.getNetworkSwitch().getAddress().getAddress());
        var networkDataList = getNetworksFromDomain(router.retrieveNetworks());

        var switchJson = new SwitchJson(switchIdJson, ipJson, switchTypeJson, networkDataList);

        return new RouterJson(routerId, routerTypeJson, switchJson);
    }

    private static List<Network> getNetworksFromJson(List<NetworkJson> networkJson){
        List<Network> networks = new ArrayList<>();
        networkJson.forEach(json ->{
            var network = new Network(
                    IP.fromAddress(json.getIp().getAddress()),
                    json.getNetworkName(),
                    Integer.valueOf(json.getCidr()));
            networks.add(network);
        });
        return networks;
    }

    private static List<NetworkJson>  getNetworksFromDomain(List<Network> networks){
        List<NetworkJson> networkJsonList = new ArrayList<>();
        networks.forEach(network -> {
            var networkJson = new NetworkJson(
                    IPJson.fromAddress(network.address().getAddress()),
                    network.name(),
                    String.valueOf(network.cidr())
            );
            networkJsonList.add(networkJson);
        });
        return networkJsonList;
    }
}
