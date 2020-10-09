package com.example.demo.awx.organization.entity;

import com.example.demo.awx.organization.entity.mapper.AwxOrganizationMapper;
import com.example.demo.awx.organization.entity.model.AwxOrganization;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AwxOrganizationMapperTest {

    @Test
    public void whenEntityIsNullThenReturnNull() {

        Assertions.assertNull(AwxOrganizationMapper.map(null));
    }

    @Test
    public void whenEntityIsValidThenReturnNotNull() {

        Assertions.assertNotNull(AwxOrganizationMapper.map(new AwxOrganizationEntity()));
    }

    @Test
    public void whenEntityHasIdThenReturnId() {

        AwxOrganizationEntity entity = new AwxOrganizationEntity();
        entity.setId("id");

        AwxOrganization awxOrganization = AwxOrganizationMapper.map(entity);

        Assertions.assertEquals("id", awxOrganization.getId());
    }

    @Test
    public void whenEntityHasNullIdThenReturnNull() {

        AwxOrganization awxOrganization = AwxOrganizationMapper.map(new AwxOrganizationEntity());

        Assertions.assertNull(awxOrganization.getId());
    }

    @Test
    public void whenEntityHasOrganizationIdThenReturnOrganizationId() {

        AwxOrganizationEntity entity = new AwxOrganizationEntity();
        entity.setOrganizationId(1L);

        AwxOrganization awxOrganization = AwxOrganizationMapper.map(entity);

        Assertions.assertEquals(1L, awxOrganization.getOrganizationId());
    }

    @Test
    public void whenEntityHasNullOrganizationThenReturnNull() {

        AwxOrganization awxOrganization = AwxOrganizationMapper.map(new AwxOrganizationEntity());

        Assertions.assertNull(awxOrganization.getOrganizationId());
    }

    @Test
    public void whenEntityHasNameThenReturnName() {

        AwxOrganizationEntity entity = new AwxOrganizationEntity();
        entity.setName("name");

        AwxOrganization awxOrganization = AwxOrganizationMapper.map(entity);

        Assertions.assertEquals("name", awxOrganization.getName());
    }

    @Test
    public void whenEntityHasNullNameThenReturnNull() {

        AwxOrganization awxOrganization = AwxOrganizationMapper.map(new AwxOrganizationEntity());

        Assertions.assertNull(awxOrganization.getName());
    }

    @Test
    public void whenEntityHasDescriptionThenReturnDescription() {

        AwxOrganizationEntity entity = new AwxOrganizationEntity();
        entity.setDescription("description");

        AwxOrganization awxOrganization = AwxOrganizationMapper.map(entity);

        Assertions.assertEquals("description", awxOrganization.getDescription());
    }

    @Test
    public void whenEntityHasNullDescriptionThenReturnNull() {

        AwxOrganization awxOrganization = AwxOrganizationMapper.map(new AwxOrganizationEntity());

        Assertions.assertNull(awxOrganization.getDescription());
    }
}
