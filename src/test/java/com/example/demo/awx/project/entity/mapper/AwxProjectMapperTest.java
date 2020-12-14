package com.example.demo.awx.project.entity.mapper;

import com.example.demo.awx.project.entity.AwxProjectEntity;
import com.example.demo.awx.project.entity.model.AwxProject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AwxProjectMapperTest {

    @Test
    public void whenEntityIsNullThenReturnNull() {

        Assertions.assertNull(AwxProjectMapper.map(null));
    }

    @Test
    public void whenEntityIsValidThenReturnNotNull() {

        Assertions.assertNotNull(AwxProjectMapper.map(new AwxProjectEntity()));
    }

    @Test
    public void whenEntityHasIdThenReturnId() {

        AwxProjectEntity entity = new AwxProjectEntity();
        entity.setId("id");

        AwxProject awxProject = AwxProjectMapper.map(entity);

        Assertions.assertEquals("id", awxProject.getId());
    }

    @Test
    public void whenEntityHasNullIdThenReturnNull() {

        AwxProject awxProject = AwxProjectMapper.map(new AwxProjectEntity());

        Assertions.assertNull(awxProject.getId());
    }

    @Test
    public void whenEntityHasAwxIdThenReturnAwxId() {

        AwxProjectEntity entity = new AwxProjectEntity();
        entity.setAwxId(1L);

        AwxProject awxProject = AwxProjectMapper.map(entity);

        Assertions.assertEquals(1L, awxProject.getAwxId());
    }

    @Test
    public void whenEntityHasNullAwxIdThenReturnNull() {

        AwxProject awxProject = AwxProjectMapper.map(new AwxProjectEntity());

        Assertions.assertNull(awxProject.getAwxId());
    }

    @Test
    public void whenEntityHasNameThenReturnName() {

        AwxProjectEntity entity = new AwxProjectEntity();
        entity.setName("name");

        AwxProject awxProject = AwxProjectMapper.map(entity);

        Assertions.assertEquals("name", awxProject.getName());
    }

    @Test
    public void whenEntityHasNullNameThenReturnNull() {

        AwxProject awxProject = AwxProjectMapper.map(new AwxProjectEntity());

        Assertions.assertNull(awxProject.getName());
    }

    @Test
    public void whenEntityHasDescriptionThenReturnDescription() {

        AwxProjectEntity entity = new AwxProjectEntity();
        entity.setDescription("description");

        AwxProject awxProject = AwxProjectMapper.map(entity);

        Assertions.assertEquals("description", awxProject.getDescription());
    }

    @Test
    public void whenEntityHasNullDescriptionThenReturnNull() {

        AwxProject awxProject = AwxProjectMapper.map(new AwxProjectEntity());

        Assertions.assertNull(awxProject.getDescription());
    }

    @Test
    public void whenEntityHasScmTypeThenReturnScmType() {

        AwxProjectEntity entity = new AwxProjectEntity();
        entity.setScmType("scm type");

        AwxProject awxProject = AwxProjectMapper.map(entity);

        Assertions.assertEquals("scm type", awxProject.getScmType());
    }

    @Test
    public void whenEntityHasNullScmTypeThenReturnScmType() {

        AwxProject awxProject = AwxProjectMapper.map(new AwxProjectEntity());

        Assertions.assertNull(awxProject.getScmType());
    }

    @Test
    public void whenEntityHasScmUrlThenReturnScmUrl() {

        AwxProjectEntity entity = new AwxProjectEntity();
        entity.setScmUrl("scm url");

        AwxProject awxProject = AwxProjectMapper.map(entity);

        Assertions.assertEquals("scm url", awxProject.getScmUrl());
    }

    @Test
    public void whenEntityHasNullScmThenReturnNull() {

        AwxProject awxProject = AwxProjectMapper.map(new AwxProjectEntity());

        Assertions.assertNull(awxProject.getScmUrl());
    }

    @Test
    public void whenEntityHasScmBranchThenReturnScmBranch() {

        AwxProjectEntity entity = new AwxProjectEntity();
        entity.setScmBranch("scm branch");

        AwxProject awxProject = AwxProjectMapper.map(entity);

        Assertions.assertEquals("scm branch", awxProject.getScmBranch());
    }

    @Test
    public void whenEntityHasNullScmBranchThenReturnNull() {

        AwxProject awxProject = AwxProjectMapper.map(new AwxProjectEntity());

        Assertions.assertNull(awxProject.getScmBranch());
    }
}
