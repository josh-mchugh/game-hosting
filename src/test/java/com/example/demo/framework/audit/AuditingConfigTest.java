package com.example.demo.framework.audit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AuditingConfigTest {

    @Test
    public void whenConfigHasAuditAwareThenReturnAuditAware() {

        AuditingConfig config = new AuditingConfig();

        Assertions.assertNotNull(config.auditorAware());
    }

    @Test
    public void whenConfigGetCurrentAuditorThenReturnSystem() {

       AuditingConfig config = new AuditingConfig();

        Assertions.assertEquals("SYSTEM", config.auditorAware().getCurrentAuditor().get());
    }
}
