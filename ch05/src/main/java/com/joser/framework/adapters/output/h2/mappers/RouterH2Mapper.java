package com.joser.framework.adapters.output.h2.mappers;

import com.joser.domain.entity.Router;
import com.joser.domain.entity.Switch;
import com.joser.domain.valueobject.IP;
import com.joser.domain.valueobject.Network;
import com.joser.domain.valueobject.RouterId;
import com.joser.domain.valueobject.RouterType;
import com.joser.domain.valueobject.SwitchId;
import com.joser.domain.valueobject.SwitchType;
import com.joser.framework.adapters.output.h2.data.IPData;
import com.joser.framework.adapters.output.h2.data.NetworkData;
import com.joser.framework.adapters.output.h2.data.RouterData;
import com.joser.framework.adapters.output.h2.data.RouterTypeData;
import com.joser.framework.adapters.output.h2.data.SwitchData;
import com.joser.framework.adapters.output.h2.data.SwitchTypeData;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RouterH2Mapper {

    public static Router toDomain(RouterData routerData) {
        var routerType = RouterType.valueOf(routerData.getRouterType().name());
        var routerId = RouterId.withUUID(routerData.getRouterId().toString());
        var switchId = SwitchId.withId(routerData.getNetworkSwitch().getSwitchId().toString());
        var switchType = SwitchType.valueOf(routerData.getNetworkSwitch().getSwitchType().name());
        var ip = IP.fromAddress(routerData.getNetworkSwitch().getIp().getAddress());
        var networks = getNetworksFromData(routerData.getNetworkSwitch().getNetworks());

        var networkSwitch = new Switch(switchType, switchId, networks, ip);
        return new Router(routerType, routerId, networkSwitch);
    }

    public static RouterData toH2(Router router) {
        var routerTypeData = RouterTypeData.valueOf(router.getType().toString());
        var routerId = router.getId().getId();
        var switchId = router.getNetworkSwitch().getSwitchId().getId();
        var switchTypeData = SwitchTypeData.valueOf(router.getNetworkSwitch().getSwitchType().toString());
        var ipData = IPData.fromAddress(router.getNetworkSwitch().getAddress().getAddress());
        var networkDataList = getNetworksFromDomain(router.retrieveNetworks(), routerId);

        var switchData = new SwitchData(
                routerId,
                switchId,
                switchTypeData,
                networkDataList,
                ipData);
        return new RouterData(routerId, routerTypeData, switchData);
    }

    private static List<Network> getNetworksFromData(List<NetworkData> networkData){
        List<Network> networks = new ArrayList<>();
        networkData.forEach(data ->{
            var network = new Network(
                    IP.fromAddress(data.getIp().getAddress()),
                    data.getName(),
                    data.getCidr());
            networks.add(network);
        });
        return networks;
    }

    private static List<NetworkData> getNetworksFromDomain(List<Network> networks, UUID switchId){
        List<NetworkData> networkDataList = new ArrayList<>();
        networks.forEach(network ->{
            var networkData = new NetworkData(
                    switchId,
                    IPData.fromAddress(network.address().getAddress()),
                    network.name(),
                    network.cidr()
            );
            networkDataList.add(networkData);
        });
        return networkDataList;
    }

}
