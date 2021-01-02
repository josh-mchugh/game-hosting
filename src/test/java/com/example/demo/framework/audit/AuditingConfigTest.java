package com.example.demo.framework.audit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AuditingConfigTest {

    @Test
    public void whenConfigHasAuditAwareThenReturnAuditAware() {

        AuditorAwareImpl auditorAware = new AuditorAwareImpl();

        AuditingConfig config = new AuditingConfig(auditorAware);

        Assertions.assertEquals(auditorAware, config.auditorAware());
    }
}
