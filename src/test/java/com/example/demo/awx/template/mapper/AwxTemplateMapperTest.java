package com.example.demo.awx.template.mapper;

import com.example.demo.awx.template.entity.AwxTemplateEntity;
import com.example.demo.awx.template.entity.TemplateJobType;
import com.example.demo.awx.template.entity.TemplateVerbosity;
import com.example.demo.awx.template.model.AwxTemplate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AwxTemplateMapperTest {

    @Test
    public void whenEntityIsNullThenReturnNull() {

        Assertions.assertNull(AwxTemplateMapper.map(null));
    }

    @Test
    public void whenEntityIsValidThenReturnNotNull() {

        Assertions.assertNotNull(AwxTemplateMapper.map(new AwxTemplateEntity()));
    }

    @Test
    public void whenEntityHasIdThenReturnId() {

        AwxTemplateEntity entity = new AwxTemplateEntity();
        entity.setId("id");

        AwxTemplate awxTemplate = AwxTemplateMapper.map(entity);

        Assertions.assertEquals("id", awxTemplate.getId());
    }

    @Test
    public void whenEntityHasNullIdThenReturnNull() {

        AwxTemplate awxTemplate = AwxTemplateMapper.map(new AwxTemplateEntity());

        Assertions.assertNull(awxTemplate.getId());
    }

    @Test
    public void whenEntityHasTemplateIdThenReturnTemplateId() {

        AwxTemplateEntity entity = new AwxTemplateEntity();
        entity.setTemplateId(1L);

        AwxTemplate awxTemplate = AwxTemplateMapper.map(entity);

        Assertions.assertEquals(1L, awxTemplate.getTemplateId());
    }

    @Test
    public void whenEntityHasNullTemplateIdThenReturnNull() {

        AwxTemplate awxTemplate = AwxTemplateMapper.map(new AwxTemplateEntity());

        Assertions.assertNull(awxTemplate.getTemplateId());
    }

    @Test
    public void whenEntityHasNameThenReturnName() {

        AwxTemplateEntity entity = new AwxTemplateEntity();
        entity.setName("name");

        AwxTemplate awxTemplate = AwxTemplateMapper.map(entity);

        Assertions.assertEquals("name", awxTemplate.getName());
    }

    @Test
    public void whenEntityHasNullNameThenReturnNull() {

        AwxTemplate awxTemplate = AwxTemplateMapper.map(new AwxTemplateEntity());

        Assertions.assertNull(awxTemplate.getName());
    }

    @Test
    public void whenEntityHasDescriptionThenReturnDescription() {

        AwxTemplateEntity entity = new AwxTemplateEntity();
        entity.setDescription("description");

        AwxTemplate awxTemplate = AwxTemplateMapper.map(entity);

        Assertions.assertEquals("description", awxTemplate.getDescription());
    }

    @Test
    public void whenEntityHasNullDescriptionThenReturnNull() {

        AwxTemplate awxTemplate = AwxTemplateMapper.map(new AwxTemplateEntity());

        Assertions.assertNull(awxTemplate.getDescription());
    }

    @Test
    public void whenEntityHasJobTypeThenReturnJobType() {

        AwxTemplateEntity entity = new AwxTemplateEntity();
        entity.setJobType(TemplateJobType.RUN);

        AwxTemplate awxTemplate = AwxTemplateMapper.map(entity);

        Assertions.assertEquals(TemplateJobType.RUN, awxTemplate.getJobType());
    }

    @Test
    public void whenEntityHasNullJobTypeThenReturnNull() {

        AwxTemplate awxTemplate = AwxTemplateMapper.map(new AwxTemplateEntity());

        Assertions.assertNull(awxTemplate.getJobType());
    }

    @Test
    public void whenEntityHasVerbosityThenReturnVerbosity() {

        AwxTemplateEntity entity = new AwxTemplateEntity();
        entity.setVerbosity(TemplateVerbosity.NORMAL);

        AwxTemplate awxTemplate = AwxTemplateMapper.map(entity);

        Assertions.assertEquals(TemplateVerbosity.NORMAL, awxTemplate.getVerbosity());
    }

    @Test
    public void whenEntityHasNullVerbosityThenReturnNull() {

        AwxTemplate awxTemplate = AwxTemplateMapper.map(new AwxTemplateEntity());

        Assertions.assertNull(awxTemplate.getVerbosity());
    }
}
