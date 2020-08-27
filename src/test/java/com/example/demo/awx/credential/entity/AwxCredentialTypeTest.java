package com.example.demo.awx.credential.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AwxCredentialTypeTest {

    @Test
    public void whenTypeIsMachineThenReturnId() {

        Assertions.assertEquals(1, AwxCredentialType.MACHINE.getId());
    }

    @Test
    public void whenTypeIsMachineThenReturnName() {

        Assertions.assertEquals("machine", AwxCredentialType.MACHINE.getName());
    }

    @Test
    public void whenTypeIsSourceControlThenReturnId() {

        Assertions.assertEquals(2, AwxCredentialType.SOURCE_CONTROL.getId());
    }

    @Test
    public void whenTypeIsSourceControlThenReturnName() {

        Assertions.assertEquals("source control", AwxCredentialType.SOURCE_CONTROL.getName());
    }
}
