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
}