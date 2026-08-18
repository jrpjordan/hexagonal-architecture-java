package com.joser.topologyinventory.domain.entity.factory;

import com.joser.topologyinventory.domain.entity.CoreRouter;
import com.joser.topologyinventory.domain.entity.EdgeRouter;
import com.joser.topologyinventory.domain.entity.Router;
import com.joser.topologyinventory.domain.vo.IP;
import com.joser.topologyinventory.domain.vo.Id;
import com.joser.topologyinventory.domain.vo.Location;
import com.joser.topologyinventory.domain.vo.Model;
import com.joser.topologyinventory.domain.vo.RouterType;
import com.joser.topologyinventory.domain.vo.Vendor;

public class RouterFactory {

    public static Router getRouter(Id id, Vendor vendor, Model model,
                                    IP ip, Location location, RouterType routerType) {
          
        switch (routerType) {
            case CORE -> {
                return CoreRouter.builder().
                    id(id == null ? Id.withoutId() : id).
                    vendor(vendor).
                    model(model).
                    ip(ip).
                    location(location).
                    routerType(routerType).
                    build();
            }
            case EDGE -> {
                return EdgeRouter.builder().
                    id(id == null ? Id.withoutId() : id).
                    vendor(vendor).
                    model(model).
                    ip(ip).
                    location(location).
                    routerType(routerType).
                    build();
            }
            default -> throw new UnsupportedOperationException ("No valid routerType informed");
        }
    }
    
}
