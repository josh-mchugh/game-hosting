package com.example.demo.web.project.create.form;

import com.google.common.collect.ImmutableMap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProjectCreateServerFormTest {

    @Test
    public void whenModelHasSelectedServerIdThenReturnSelectedServerId() {

        ProjectCreateServerForm model = new ProjectCreateServerForm();
        model.setSelectedServerId("selectedServerId");

        Assertions.assertEquals("selectedServerId", model.getSelectedServerId());
    }

    @Test
    public void whenModelHasAvailableServersThenReturnAvailableServers() {

        ProjectCreateServerForm form = new ProjectCreateServerForm();
        form.setAvailableServers(ImmutableMap.of());

        Assertions.assertEquals(ImmutableMap.of(), form.getAvailableServers());
    }

    @Test
    public void whenModelToString() {

        ProjectCreateServerForm model = model();

        String expected = "ProjectCreateServerForm(selectedServerId=selectedServerId, availableServers={})";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        ProjectCreateServerForm model = model();

        Assertions.assertEquals(1678438012, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        ProjectCreateServerForm model1 = model();
        ProjectCreateServerForm model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        ProjectCreateServerForm model = model();

        Assertions.assertNotEquals(new ProjectCreateServerForm(), model);
    }

    private ProjectCreateServerForm model() {

        ProjectCreateServerForm model = new ProjectCreateServerForm();
        model.setSelectedServerId("selectedServerId");
        model.setAvailableServers(ImmutableMap.of());

        return model;
    }
}
