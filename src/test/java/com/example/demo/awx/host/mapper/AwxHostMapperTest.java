package com.example.demo.awx.host.mapper;

import com.example.demo.awx.host.entity.AwxHostEntity;
import com.example.demo.awx.host.model.AwxHost;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AwxHostMapperTest {

    @Test
    public void whenEntityIsNullThenReturnNull() {

        Assertions.assertNull(AwxHostMapper.map(null));
    }

    @Test
    public void whenEntityIsValueThenReturnNotNull() {

        Assertions.assertNotNull(AwxHostMapper.map(new AwxHostEntity()));
    }

    @Test
    public void whenEntityHasIdThenReturnId() {

        AwxHostEntity entity = new AwxHostEntity();
        entity.setId("id");

        AwxHost awxHost = AwxHostMapper.map(entity);

        Assertions.assertEquals("id", awxHost.getId());
    }

    @Test
    public void whenEntityHasNullIdThenReturnNull() {

        AwxHost awxHost = AwxHostMapper.map(new AwxHostEntity());

        Assertions.assertNull(awxHost.getId());
    }

    @Test
    public void whenEntityHasHostnameThenReturnHostname() {

        AwxHostEntity entity = new AwxHostEntity();
        entity.setHostname("hostname");

        AwxHost awxHost = AwxHostMapper.map(entity);

        Assertions.assertEquals("hostname", awxHost.getHostname());
    }

    @Test
    public void whenEntityHasNullHostNameThenReturnNull() {

        AwxHost awxHost = AwxHostMapper.map(new AwxHostEntity());

        Assertions.assertNull(awxHost.getHostname());
    }

    @Test
    public void whenEntityHasDescriptionThenReturnDescription() {

        AwxHostEntity entity = new AwxHostEntity();
        entity.setDescription("description");

        AwxHost awxHost = AwxHostMapper.map(entity);

        Assertions.assertEquals("description", awxHost.getDescription());
    }

    @Test
    public void whenEntityHasNullDescriptionThenReturnNull() {

        AwxHost awxHost = AwxHostMapper.map(new AwxHostEntity());

        Assertions.assertNull(awxHost.getDescription());
    }
}
