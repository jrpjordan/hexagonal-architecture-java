module application {
    requires domain;
    requires static lombok;

    exports com.joser.topologyinventory.application.usecases;
    exports com.joser.topologyinventory.application.ports.output;
    exports com.joser.topologyinventory.application.ports.input;

    provides com.joser.topologyinventory.application.usecases.RouterManagementUseCase
            with com.joser.topologyinventory.application.ports.input.RouterManagementInputPort;
    provides com.joser.topologyinventory.application.usecases.SwitchManagementUseCase
            with com.joser.topologyinventory.application.ports.input.SwitchManagementInputPort;
    provides com.joser.topologyinventory.application.usecases.NetworkManagementUseCase
            with com.joser.topologyinventory.application.ports.input.NetworkManagementInputPort;
}