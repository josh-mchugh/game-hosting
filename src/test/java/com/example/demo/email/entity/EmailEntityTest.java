package com.example.demo.email.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class EmailEntityTest {

    @Test
    public void whenEntityHasIdThenReturnId() {

        EmailEntity entity = new EmailEntity();
        entity.setId("id");

        Assertions.assertEquals("id", entity.getId());
    }

    @Test
    public void whenEntityHasUUIDThenReturnId() {

        UUID id = UUID.randomUUID();

        EmailEntity entity = new EmailEntity();
        entity.setId(id);

        Assertions.assertEquals(id.toString(), entity.getId());
    }

    @Test
    public void whenEntityHasCreatedByThenReturnCreatedBy() {

        EmailEntity entity = new EmailEntity();
        entity.setCreatedBy("created-by");

        Assertions.assertEquals("created-by", entity.getCreatedBy());
    }

    @Test
    public void whenEntityHasCreatedDateThenReturnCreatedDate() {

        LocalDateTime createdDate = LocalDateTime.now();

        EmailEntity entity = new EmailEntity();
        entity.setCreatedDate(createdDate);

        Assertions.assertEquals(createdDate, entity.getCreatedDate());
    }

    @Test
    public void whenEntityHasLastModifiedByThenReturnLastModifiedBy() {

        EmailEntity entity = new EmailEntity();
        entity.setLastModifiedBy("last-modified-by");

        Assertions.assertEquals("last-modified-by", entity.getLastModifiedBy());
    }

    @Test
    public void whenEntityHasLastModifiedDateThenReturnLastModifiedDate() {

        LocalDateTime lastModifiedDate = LocalDateTime.now();

        EmailEntity entity = new EmailEntity();
        entity.setLastModifiedDate(lastModifiedDate);

        Assertions.assertEquals(lastModifiedDate, entity.getLastModifiedDate());
    }

    @Test
    public void whenEntityHasVersionThenReturnVersion() {

        EmailEntity entity = new EmailEntity();
        entity.setVersion(1L);

        Assertions.assertEquals(1L, entity.getVersion());
    }

    @Test
    public void whenEntityHasTemplateThenReturnTemplate() {

        EmailEntity entity = new EmailEntity();
        entity.setTemplate(EmailTemplate.WELCOME);

        Assertions.assertEquals(EmailTemplate.WELCOME, entity.getTemplate());
    }

    @Test
    public void whenEntityHasStatusThenReturnStatus() {

        EmailEntity entity = new EmailEntity();
        entity.setStatus(EmailStatus.SENT);

        Assertions.assertEquals(EmailStatus.SENT, entity.getStatus());
    }

    @Test
    public void whenEntityHasToAddressThenReturnToAddress() {

        EmailEntity entity = new EmailEntity();
        entity.setToAddress("toAddress");

        Assertions.assertEquals("toAddress", entity.getToAddress());
    }

    @Test
    public void whenEntityHasBodyContextThenReturnBodyContext() {

        Map<String, Object> map = new HashMap<>();
        map.put("test", "test");

        EmailEntity entity = new EmailEntity();
        entity.setBodyContext(map);

        Map<String, Object> expected = new HashMap<>();
        expected.put("test", "test");

        Assertions.assertEquals(expected, entity.getBodyContext());
    }

    @Test
    public void whenEntityHasSubjectContextThenReturnSubjectContext() {

        EmailEntity entity = new EmailEntity();
        entity.setSubjectContext(Arrays.asList("test", "test"));

        Assertions.assertEquals(Arrays.asList("test", "test"), entity.getSubjectContext());
    }

    @Test
    public void whenEntityHasSentDateThenReturnSentDate() {

        LocalDateTime sentDate = LocalDateTime.now();

        EmailEntity entity = new EmailEntity();
        entity.setSentDate(sentDate);

        Assertions.assertEquals(sentDate, entity.getSentDate());
    }
}
