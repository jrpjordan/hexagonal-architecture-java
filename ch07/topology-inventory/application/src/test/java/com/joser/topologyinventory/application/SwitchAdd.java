package com.joser.topologyinventory.application;

import com.joser.topologyinventory.domain.entity.Switch;
import com.joser.topologyinventory.domain.vo.IP;
import com.joser.topologyinventory.domain.vo.Id;
import com.joser.topologyinventory.domain.vo.Model;
import com.joser.topologyinventory.domain.vo.SwitchType;
import com.joser.topologyinventory.domain.vo.Vendor;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SwitchAdd extends ApplicationTestData {

    public SwitchAdd() {
        loadData();
    }

    @Given("I provide a switch")
    public void i_provide_a_switch() {
        networkSwitch = Switch.builder().
                id(Id.withId("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3490")).
                vendor(Vendor.CISCO).
                model(Model.XYZ0004).
                ip(IP.fromAddress("20.0.0.100")).
                location(locationA).
                switchType(SwitchType.LAYER3).
                build();
        assertNotNull(networkSwitch);
    }

    @Then("I add the switch to the edge router")
    public void i_add_the_switch_to_the_edge_router() {
        assertNotNull(edgeRouter);
        edgeRouter = this.switchManagementUseCase.
                addSwitchToEdgeRouter(networkSwitch, edgeRouter);
        var actualId = networkSwitch.getId();
        var expectedId = edgeRouter.getSwitches().
                get(Id.withId("\"f8c3de3d-1fea-4d7c-a8b0-29f63c4c3490")).
                getId();
        assertEquals(expectedId, actualId);
    }
}
