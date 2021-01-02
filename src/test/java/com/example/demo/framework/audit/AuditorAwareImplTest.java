package com.example.demo.framework.audit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AuditorAwareImplTest {

    @Test
    public void whenConfigGetCurrentAuditorThenReturnSystem() {

        AuditorAwareImpl auditorAware = new AuditorAwareImpl();

        Assertions.assertEquals("SYSTEM", auditorAware.getCurrentAuditor().get());
    }
}
