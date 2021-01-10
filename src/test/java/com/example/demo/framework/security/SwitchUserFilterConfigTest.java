package com.example.demo.framework.security;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class SwitchUserFilterConfigTest {

    @Test
    public void whenConfigHasSwitchUserFilterThenReturnSwitchUserFilter() {

        UserDetailsServiceImpl userDetailsService = Mockito.mock(UserDetailsServiceImpl.class);

        SwitchUserFilterConfig config = new SwitchUserFilterConfig(userDetailsService);

        Assertions.assertNotNull(config.switchUserFilter());
    }
}
