package com.joser.domain.service;

import com.joser.domain.entity.Router;
import com.joser.domain.specifications.CIDRSpecification;
import com.joser.domain.specifications.NetworkAmountSpecification;
import com.joser.domain.specifications.NetworkAvailabilitySpecification;
import com.joser.domain.specifications.RouterTypeSpecification;
import com.joser.domain.valueobject.IP;
import com.joser.domain.valueobject.Network;

public class NetworkOperation {

    public static Router createNewNetwork(Router router, Network network) {
        var availabilitySpec = new NetworkAvailabilitySpecification(network.address(), network.name(), network.cidr());
        var cidrSpec = new CIDRSpecification();
        var routerTypeSpec = new RouterTypeSpecification();
        var amountSpec = new NetworkAmountSpecification();

        if (!cidrSpec.isSatisfiedBy(network.cidr()))
            throw new IllegalArgumentException("cidr is below " + CIDRSpecification.MINIMUM_ALLOWED_CIDR);
        if (!availabilitySpec.isSatisfiedBy(router))
            throw new IllegalArgumentException("Address already exists");
        if (amountSpec.and(routerTypeSpec).isSatisfiedBy(router)) {
            Network newNetwork = router
                    .createNetwork(
                            network.address(),
                            network.name(),
                            network.cidr()
                    );
            router.addNetworkToSwitch(network);
        }
        return router;
    }

}
