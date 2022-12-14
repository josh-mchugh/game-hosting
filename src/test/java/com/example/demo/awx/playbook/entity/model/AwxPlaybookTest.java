package com.example.demo.awx.playbook.entity.model;

import com.example.demo.awx.playbook.entity.PlaybookType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class AwxPlaybookTest {

    @Test
    public void whenModelHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        AwxPlaybook model = AwxPlaybook.builder()
                .id(id)
                .build();

        Assertions.assertEquals(id, model.getId());
    }

    @Test
    public void whenModelHasNameThenReturnName() {

        AwxPlaybook model = AwxPlaybook.builder()
                .name("name")
                .build();

        Assertions.assertEquals("name", model.getName());
    }

    @Test
    public void whenModelHasTypeThenReturnType() {

        AwxPlaybook model = AwxPlaybook.builder()
                .type(PlaybookType.TEST)
                .build();

        Assertions.assertEquals(PlaybookType.TEST, model.getType());
    }

    @Test
    public void whenModelToString() {

        AwxPlaybook model = model();

        String expected = "AwxPlaybook(id=ec7e003c-2d75-423b-b085-e6cc5e79a93c, name=name, type=TEST)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        AwxPlaybook model = AwxPlaybook.builder()
                .id(UUID.fromString("ec7e003c-2d75-423b-b085-e6cc5e79a93c"))
                .name("name")
                .build();

        Assertions.assertEquals(1111311382, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        AwxPlaybook model1 = model();
        AwxPlaybook model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        AwxPlaybook model = model();

        Assertions.assertNotEquals(model, AwxPlaybook.builder().build());
    }

    private AwxPlaybook model() {

        return AwxPlaybook.builder()
                .id(UUID.fromString("ec7e003c-2d75-423b-b085-e6cc5e79a93c"))
                .name("name")
                .type(PlaybookType.TEST)
                .build();
    }
}
