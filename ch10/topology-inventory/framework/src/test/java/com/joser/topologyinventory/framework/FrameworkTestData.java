package com.joser.topologyinventory.framework;

import com.joser.topologyinventory.application.ports.output.RouterManagementOutputPort;
import com.joser.topologyinventory.application.ports.output.SwitchManagementOutputPort;
import com.joser.topologyinventory.application.usecases.NetworkManagementUseCase;
import com.joser.topologyinventory.application.usecases.RouterManagementUseCase;
import com.joser.topologyinventory.application.usecases.SwitchManagementUseCase;
import com.joser.topologyinventory.domain.entity.CoreRouter;
import com.joser.topologyinventory.domain.entity.EdgeRouter;
import com.joser.topologyinventory.domain.entity.Router;
import com.joser.topologyinventory.domain.entity.Switch;
import com.joser.topologyinventory.domain.vo.IP;
import com.joser.topologyinventory.domain.vo.Id;
import com.joser.topologyinventory.domain.vo.Location;
import com.joser.topologyinventory.domain.vo.Model;
import com.joser.topologyinventory.domain.vo.Network;
import com.joser.topologyinventory.domain.vo.RouterType;
import com.joser.topologyinventory.domain.vo.SwitchType;
import com.joser.topologyinventory.domain.vo.Vendor;
import com.joser.topologyinventory.framework.adapters.input.generic.NetworkManagementGenericAdapter;
import com.joser.topologyinventory.framework.adapters.input.generic.RouterManagementGenericAdapter;
import com.joser.topologyinventory.framework.adapters.input.generic.SwitchManagementGenericAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;

public class FrameworkTestData {

    protected List<Router> routers = new ArrayList<>();

    protected List<Switch> switches = new ArrayList<>();

    protected List<Network> networks = new ArrayList<>();

    protected Map<Id, Router> routersOfCoreRouter = new HashMap<>();

    protected Map<Id, Switch> switchesOfEdgeRouter = new HashMap<>();

    protected Network network;

    protected Switch networkSwitch;

    protected CoreRouter coreRouter;

    protected CoreRouter newCoreRouter;

    protected EdgeRouter edgeRouter;

    protected EdgeRouter newEdgeRouter;

    protected Location locationA;

    protected Location locationB;

    protected RouterManagementGenericAdapter routerManagementGenericAdapter;
    protected SwitchManagementGenericAdapter switchManagementGenericAdapter;
    protected NetworkManagementGenericAdapter networkManagementGenericAdapter;

    protected void loadPortsAndUseCases() {
        // Load router implementations
        ServiceLoader<RouterManagementUseCase> loaderUseCaseRouter = ServiceLoader.load(RouterManagementUseCase.class);
        RouterManagementUseCase routerManagementUseCase = loaderUseCaseRouter.findFirst()
                .orElseThrow(() -> new IllegalStateException("RouterManagementUseCase not found in ServiceLoader"));
        ServiceLoader<RouterManagementOutputPort> loaderOutputRouter = ServiceLoader.load(RouterManagementOutputPort.class);
        RouterManagementOutputPort routerManagementOutputPort = loaderOutputRouter.findFirst()
                .orElseThrow(() -> new IllegalStateException("RouterManagementOutputPort not found in ServiceLoader"));

        // Load switch implementations
        ServiceLoader<SwitchManagementUseCase> loaderUseCaseSwitch = ServiceLoader.load(SwitchManagementUseCase.class);
        SwitchManagementUseCase switchManagementUseCase = loaderUseCaseSwitch.findFirst()
                .orElseThrow(() -> new IllegalStateException("SwitchManagementUseCase not found in ServiceLoader"));
        ServiceLoader<SwitchManagementOutputPort> loaderOutputSwitch = ServiceLoader.load(SwitchManagementOutputPort.class);
        SwitchManagementOutputPort switchManagementOutputPort = loaderOutputSwitch.findFirst()
                .orElseThrow(() -> new IllegalStateException("SwitchManagementOutputPort not found in ServiceLoader"));

        // Load network implementations
        ServiceLoader<NetworkManagementUseCase> loaderUseCaseNetwork = ServiceLoader.load(NetworkManagementUseCase.class);
        NetworkManagementUseCase networkManagementUseCase = loaderUseCaseNetwork.findFirst()
                .orElseThrow(() -> new IllegalStateException("NetworkManagementUseCase not found in ServiceLoader"));

        routerManagementUseCase.setOutputPort(routerManagementOutputPort);
        switchManagementUseCase.setOutputPort(switchManagementOutputPort);
        networkManagementUseCase.setOutputPort(routerManagementOutputPort);

        this.routerManagementGenericAdapter = new RouterManagementGenericAdapter(routerManagementUseCase);
        this.switchManagementGenericAdapter = new SwitchManagementGenericAdapter(routerManagementUseCase, switchManagementUseCase);
        this.networkManagementGenericAdapter = new NetworkManagementGenericAdapter(switchManagementUseCase, networkManagementUseCase);
    }

    public void loadData(){
        this.locationA = new Location(
                "Amos Ln",
                "Tully",
                "NY",
                13159,
                "United States",
                42.797310F,
                -76.130750F);
        this.locationB = new Location(
                "Av Republica Argentina 3109",
                "Curitiba",
                "PR",
                80610260,
                "Brazil",
                10F,
                -10F);
        this.network  = Network.builder().
                networkAddress(IP.fromAddress("20.0.0.0")).
                networkName("TestNetwork").
                networkCidr(8).
                build();
        this.networks.add(network);
        this.networkSwitch = Switch.builder().
                id(Id.withId("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3490")).
                vendor(Vendor.CISCO).
                model(Model.XYZ0004).
                ip(IP.fromAddress("20.0.0.100")).
                location(locationA).
                switchType(SwitchType.LAYER3).
                switchNetworks(networks).
                build();
        this.switchesOfEdgeRouter.put(networkSwitch.getId(), networkSwitch);
        this.edgeRouter = EdgeRouter.builder().
                id(Id.withoutId()).
                vendor(Vendor.CISCO).
                model(Model.XYZ0002).
                ip(IP.fromAddress("20.0.0.1")).
                location(locationA).
                routerType(RouterType.EDGE).
                switches(switchesOfEdgeRouter).
                build();
        this.routersOfCoreRouter.put(edgeRouter.getId(), edgeRouter);
        this.coreRouter = CoreRouter.builder().
                id(Id.withoutId()).
                vendor(Vendor.HP).
                model(Model.XYZ0001).
                ip(IP.fromAddress("10.0.0.1")).
                location(locationA).
                routerType(RouterType.CORE).
                routers(routersOfCoreRouter).
                build();
        this.newCoreRouter = CoreRouter.builder().
                id(Id.withId("81579b05-4b4e-4b9b-91f4-75a5a8561296")).
                vendor(Vendor.HP).
                model(Model.XYZ0001).
                ip(IP.fromAddress("10.1.0.1")).
                location(locationA).
                routerType(RouterType.CORE).
                build();
        this.newEdgeRouter = EdgeRouter.builder().
                id(Id.withId("ca23800e-9b5a-11eb-a8b3-0242ac130003")).
                vendor(Vendor.CISCO).
                model(Model.XYZ0002).
                ip(IP.fromAddress("20.1.0.1")).
                location(locationA).
                routerType(RouterType.EDGE).
                build();
    }
}
