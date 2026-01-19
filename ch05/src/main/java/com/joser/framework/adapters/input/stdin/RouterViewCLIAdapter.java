package com.joser.framework.adapters.input.stdin;

import com.joser.application.ports.input.RouterViewInputPort;
import com.joser.application.usecases.RouterViewUseCase;
import com.joser.domain.entity.Router;
import com.joser.domain.valueobject.RouterType;
import com.joser.framework.adapters.output.file.RouterViewFileAdapter;

import java.util.List;

public class RouterViewCLIAdapter {

    private RouterViewUseCase routerViewUseCase;

    public RouterViewCLIAdapter() {
        setAdapters();
    }

    public List<Router> obtainRelatedRouters(String type) {
        return routerViewUseCase.getRouters(
                Router.filterRouterByType(RouterType.valueOf(type))
        );
    }

    private void setAdapters() {
        this.routerViewUseCase = new RouterViewInputPort(RouterViewFileAdapter.getInstance());
    }

}
