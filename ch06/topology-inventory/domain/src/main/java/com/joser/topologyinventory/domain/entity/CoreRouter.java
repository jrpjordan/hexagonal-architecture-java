package com.joser.topologyinventory.domain.entity;

import com.joser.topologyinventory.domain.specification.EmptyRouterSpec;
import com.joser.topologyinventory.domain.specification.EmptySwitchSpec;
import com.joser.topologyinventory.domain.specification.SameCountrySpec;
import com.joser.topologyinventory.domain.specification.SameIpSpec;
import com.joser.topologyinventory.domain.vo.IP;
import com.joser.topologyinventory.domain.vo.Id;
import com.joser.topologyinventory.domain.vo.Location;
import com.joser.topologyinventory.domain.vo.Model;
import com.joser.topologyinventory.domain.vo.RouterType;
import com.joser.topologyinventory.domain.vo.Vendor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Map;

@Getter
@ToString
public final class CoreRouter extends Router {

    @Getter
    private final Map<Id, Router> routers;

    @Builder
    public CoreRouter(Id id,
                      Vendor vendor,
                      Model model,
                      IP ip,
                      Location location,
                      RouterType routerType,
                      Map<Id, Router> routers) {
        super(id, vendor, model, ip, location, routerType);
        this.routers = routers;
    }

    public Router addRouter(Router anyRouter) {
        var sameCountryRouterSpec = new SameCountrySpec(this);
        var sameIpSpec = new SameIpSpec(this);

        sameCountryRouterSpec.check(anyRouter);
        sameIpSpec.check(anyRouter);

        return this.routers.put(anyRouter.id, anyRouter);
    }

    public Router removeRouter(Router anyRouter) {
        var emptyRouterSpec = new EmptyRouterSpec();
        var emptySwitchSpec = new EmptySwitchSpec();

        switch (anyRouter.getRouterType()) {
            case CORE -> {
                var coreRouter = (CoreRouter) anyRouter;
                emptyRouterSpec.check(coreRouter);
            }
            case EDGE -> {
                var edgeRouter = (EdgeRouter) anyRouter;
                emptySwitchSpec.check(edgeRouter);
            }
        }
        return this.routers.remove(anyRouter.id);
    }
}
