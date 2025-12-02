package com.joser.framework.adapters.input.stdin;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.joser.application.ports.input.RouterNetworkInputPort;
import com.joser.application.usecases.RouterNetworkUseCase;
import com.joser.domain.entity.Router;
import com.joser.domain.valueobject.Network;
import com.joser.domain.valueobject.RouterId;
import com.joser.framework.adapters.input.RouterNetworkAdapter;
import com.joser.framework.adapters.output.file.RouterNetworkFileAdapter;
import com.joser.framework.adapters.output.file.mappers.RouterJsonFileMapper;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class RouterNetworkCliAdapter extends RouterNetworkAdapter {

    RouterNetworkUseCase routerNetworkUseCase;

    public RouterNetworkCliAdapter(RouterNetworkUseCase routerNetworkUseCase) {
        this.routerNetworkUseCase = routerNetworkUseCase;
    }

    public Router addNetwork(RouterId routerId, Network network) {
        return routerNetworkUseCase.addNetworkToRouter(routerId, network);
    }

    @Override
    public Router processRequest(Object requestParams) {
        var params = stdinParams(requestParams);
        router = this.addNetworkToRouter(params);
        ObjectMapper mapper = new ObjectMapper();
        try {
            var routerJson = mapper.writeValueAsString (RouterJsonFileMapper.toJson(router));
            System.out.println(routerJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return router;
    }

    private Map<String, String> stdinParams(Object requestParams) {
        Map<String, String> params = new HashMap<>();
        if (requestParams instanceof Scanner) {
            var scanner = (Scanner) requestParams;
            System.out.print("Enter Router ID: ");
            var routerId = scanner.nextLine();
            params.put("routerId", routerId);
            System.out.print("Enter Network Name: ");
            var name = scanner.nextLine();
            params.put("name", name);
            System.out.print("Enter Network Address: ");
            var address = scanner.nextLine();
            params.put("address", address);
            System.out.print("Enter Network CIDR: ");
            var cidr = scanner.nextLine();
            params.put("cidr", cidr);
        } else {
            throw new IllegalArgumentException("Request with invalid parameters");
        }
        return params;
    }
}
