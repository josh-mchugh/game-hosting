package com.example.demo.framework.audit;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

@Configuration
@RequiredArgsConstructor
public class AuditingConfig {

    @Bean
    public AuditorAware<String> auditorAware() {

        return () -> Optional.of("SYSTEM");
    }
}
