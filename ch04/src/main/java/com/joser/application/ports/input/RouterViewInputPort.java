package com.joser.application.ports.input;

import com.joser.application.ports.output.RouterViewOutputPort;
import com.joser.application.usecases.RouterViewUseCase;
import com.joser.domain.entity.Router;
import com.joser.domain.service.RouterSearch;

import java.util.List;
import java.util.function.Predicate;

public class RouterViewInputPort implements RouterViewUseCase {

    private RouterViewOutputPort routerListOutputPort;

    public RouterViewInputPort(RouterViewOutputPort routerViewOutputPort) {
        this.routerListOutputPort = routerViewOutputPort;
    }

    public List<Router> getRouters(Predicate<Router> filter) {
        var routers = routerListOutputPort.fetchRouters();
        return RouterSearch.retrieveRouter(routers, filter);
    }

}
