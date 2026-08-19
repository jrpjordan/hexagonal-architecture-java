package com.joser.topologyinventory.application;

import static org.junit.jupiter.api.Assertions.*;

import com.joser.topologyinventory.domain.entity.CoreRouter;
import com.joser.topologyinventory.domain.entity.EdgeRouter;
import com.joser.topologyinventory.domain.vo.IP;
import com.joser.topologyinventory.domain.vo.Model;
import com.joser.topologyinventory.domain.vo.RouterType;
import com.joser.topologyinventory.domain.vo.Vendor;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class RouterAdd extends ApplicationTestData {

    CoreRouter anotherCoreRouter;
    
    public RouterAdd() {
        loadData();
    }

    //Adding an edge router to a core router
    @Given("I have an edge router")
    public void assert_edge_router_exists() {
        edgeRouter = (EdgeRouter) this.routerManagementUseCase.createRouter(
            Vendor.HP,
            Model.XYZ0004,
            IP.fromAddress("20.0.0.1"),
            locationA,
            RouterType.EDGE
        );
        assertNotNull(edgeRouter);
    }

    @And("I have a core router")
    public void assert_core_router_exsits() {
        coreRouter = (CoreRouter) this.routerManagementUseCase.createRouter(
            Vendor.CISCO,
            Model.XYZ0001,
            IP.fromAddress("30.0.0.1"),
            locationA,
            RouterType.CORE
        );
        assertNotNull(coreRouter);
    }

    @Then("I add and edge router to a core router")
    public void add_edge_to_core_router() {
        var actualEdgeId = edgeRouter.getId();
        var routerWithEdge = (CoreRouter) this.routerManagementUseCase.
            addRouterToCoreRouter(edgeRouter, coreRouter);
        var expectedEdgeId = routerWithEdge.getRouters().get(actualEdgeId).getId();
        assertEquals(actualEdgeId, expectedEdgeId);
    }

    //Adding a core router to another core router
    @Given("I have this core router")
    public void assert_this_core_router_exists() {
        coreRouter = (CoreRouter) this.routerManagementUseCase.createRouter(
            Vendor.CISCO,
            Model.XYZ0001,
            IP.fromAddress("30.0.0.1"),
            locationA, 
            RouterType.CORE
        );
        assertNotNull(coreRouter);
    }

    @And("I have another core router")
    public void assert_another_core_router_exists() {
        anotherCoreRouter = (CoreRouter) this.routerManagementUseCase.createRouter(
            Vendor.CISCO,
            Model.XYZ0001,
            IP.fromAddress("40.0.0.1"),
            locationA,
            RouterType.CORE
        );
        assertNotNull(anotherCoreRouter);
    }

    @Then("I add a core router to another core router")
    public void add_core_to_core_router() {
        var coreRouterId = coreRouter.getId();
        var routerWithCore = (CoreRouter) this.routerManagementUseCase.
            addRouterToCoreRouter(coreRouter, anotherCoreRouter);
        var expectedCoreId = routerWithCore.
            getRouters().get(coreRouterId).getId();
        assertEquals(coreRouterId, expectedCoreId);
    }
    
}
