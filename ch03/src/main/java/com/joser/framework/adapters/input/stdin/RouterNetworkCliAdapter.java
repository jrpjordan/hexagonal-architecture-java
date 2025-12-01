package com.joser.framework.adapters.input.stdin;

import com.joser.application.ports.input.RouterNetworkInputPort;
import com.joser.application.usecases.RouterNetworkUseCase;
import com.joser.domain.entity.Router;
import com.joser.domain.valueobject.Network;
import com.joser.domain.valueobject.RouterId;
import com.joser.framework.adapters.output.file.RouterNetworkFileAdapter;

public class RouterNetworkCliAdapter {

    RouterNetworkUseCase routerNetworkUseCase;

    public RouterNetworkCliAdapter() {
        setAdapters();
    }

    public Router addNetwork(RouterId routerId, Network network) {
        return routerNetworkUseCase.addNetworkToRouter(routerId, network);
    }

    private void setAdapters() {
        this.routerNetworkUseCase = new RouterNetworkInputPort(RouterNetworkFileAdapter.getInstance());
    }
}
