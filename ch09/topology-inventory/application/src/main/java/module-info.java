module application {
    exports com.joser.topologyinventory.application.usecases;
    exports com.joser.topologyinventory.application.ports.output;
    exports com.joser.topologyinventory.application.ports.input;
    requires domain;
    requires static lombok;
}