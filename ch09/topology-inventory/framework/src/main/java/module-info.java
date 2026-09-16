module framework {
    requires domain;
    requires application;
    requires jakarta.persistence;
    requires org.eclipse.persistence.core;
    requires java.sql;
    requires static lombok;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.core;

    exports com.joser.topologyinventory.framework.adapters.output.h2.data;
    opens com.joser.topologyinventory.framework.adapters.output.h2.data;

    provides com.joser.topologyinventory.application.ports.output.RouterManagementOutputPort
            with com.joser.topologyinventory.framework.adapters.output.h2.RouterManagementH2Adapter;
    provides com.joser.topologyinventory.application.ports.output.SwitchManagementOutputPort
            with com.joser.topologyinventory.framework.adapters.output.h2.SwitchManagementH2Adapter;

    uses com.joser.topologyinventory.application.usecases.RouterManagementUseCase;
    uses com.joser.topologyinventory.application.usecases.SwitchManagementUseCase;
    uses com.joser.topologyinventory.application.usecases.NetworkManagementUseCase;
    uses com.joser.topologyinventory.application.ports.output.RouterManagementOutputPort;
    uses com.joser.topologyinventory.application.ports.output.SwitchManagementOutputPort;
}