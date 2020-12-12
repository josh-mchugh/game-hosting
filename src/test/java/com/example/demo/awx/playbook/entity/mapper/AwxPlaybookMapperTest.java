package com.example.demo.awx.playbook.entity.mapper;

import com.example.demo.awx.playbook.entity.AwxPlayBookEntity;
import com.example.demo.awx.playbook.entity.model.AwxPlaybook;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AwxPlaybookMapperTest {

    @Test
    public void whenEntityIsNullThenReturnNull() {

        Assertions.assertNull(AwxPlaybookMapper.map(null));
    }

    @Test
    public void whenEntityIsValidThenReturnNotNull() {

        Assertions.assertNotNull(AwxPlaybookMapper.map(new AwxPlayBookEntity()));
    }

    @Test
    public void whenEntityHasIdThenReturnId() {

        AwxPlayBookEntity entity = new AwxPlayBookEntity();
        entity.setId("id");

        AwxPlaybook awxPlaybook = AwxPlaybookMapper.map(entity);

        Assertions.assertEquals("id", awxPlaybook.getId());
    }

    @Test
    public void whenEntityHasNullIdThenReturnNull() {

        AwxPlaybook awxPlaybook = AwxPlaybookMapper.map(new AwxPlayBookEntity());

        Assertions.assertNull(awxPlaybook.getId());
    }

    @Test
    public void whenEntityHasNameThenReturnName() {

        AwxPlayBookEntity entity = new AwxPlayBookEntity();
        entity.setName("name");

        AwxPlaybook awxPlaybook = AwxPlaybookMapper.map(entity);

        Assertions.assertEquals("name", awxPlaybook.getName());
    }

    @Test
    public void whenEntityHasNullNameThenReturnNull() {

        AwxPlaybook awxPlaybook = AwxPlaybookMapper.map(new AwxPlayBookEntity());

        Assertions.assertNull(awxPlaybook.getName());
    }

    @Test
    public void whenEntityHasTypeThenReturnType() {

        AwxPlayBookEntity entity = new AwxPlayBookEntity();
        entity.setName("name");

        AwxPlaybook awxPlaybook = AwxPlaybookMapper.map(entity);

        Assertions.assertEquals("name", awxPlaybook.getName());
    }

    @Test
    public void whenEntityHasNullTypeThenReturnNull() {

        AwxPlaybook awxPlaybook = AwxPlaybookMapper.map(new AwxPlayBookEntity());

        Assertions.assertNull(awxPlaybook.getType());
    }
}
