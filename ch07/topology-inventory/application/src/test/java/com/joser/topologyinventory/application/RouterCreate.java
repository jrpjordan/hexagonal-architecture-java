package com.joser.topologyinventory.application;

import com.joser.topologyinventory.domain.vo.IP;
import com.joser.topologyinventory.domain.vo.Model;
import com.joser.topologyinventory.domain.vo.RouterType;

import static com.joser.topologyinventory.domain.vo.RouterType.*;
import static org.junit.jupiter.api.Assertions.*;

import com.joser.topologyinventory.domain.vo.Vendor;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class RouterCreate extends ApplicationTestData {

    public RouterCreate() {
        loadData();
    }

    //Creating a new core router
    @Given("I provide all required data to create a core router")
    public void create_core_router() {
        router = this.routerManagementUseCase.createRouter(Vendor.CISCO,
            Model.XYZ0001,
            IP.fromAddress("20.0.0.1"),
            locationA, CORE 
        );
    }

    @Then("A new core router is created")
    public void a_new_core_router_is_created() {
        assertNotNull(router);
        assertEquals(CORE, router.getRouterType());
    }

    //Creating a new edge router
    @Given("I provide all required data to create an edge router")
    public void create_edge_router() {
        router = this.routerManagementUseCase.createRouter(
            Vendor.HP,
            Model.XYZ0004,
            IP.fromAddress("30.0.0.1"),
            locationA,
            EDGE
        );
    }

    @Then("A new edge router is created")
    public void a_new_edge_router_is_created() {
        assertNotNull(router);
        assertEquals(EDGE, router.getRouterType());
    }
}
