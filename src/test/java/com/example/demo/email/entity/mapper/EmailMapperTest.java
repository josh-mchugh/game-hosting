package com.example.demo.email.entity.mapper;

import com.example.demo.email.entity.EmailEntity;
import com.example.demo.email.entity.EmailStatus;
import com.example.demo.email.entity.EmailTemplate;
import com.example.demo.email.entity.model.Email;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class EmailMapperTest {

    @Test
    public void whenEntityIsValidThenExpectNotNull() {

        Assertions.assertNotNull(EmailMapper.map(new EmailEntity()));
    }

    @Test
    public void whenParamIsNullThenExpectNull() {

        Assertions.assertNull(EmailMapper.map(null));
    }

    @Test
    public void whenEntityHasIdThenReturnId() {

        EmailEntity entity = new EmailEntity();
        entity.setId("id");

        Email email = EmailMapper.map(entity);

        Assertions.assertEquals("id", email.getId());
    }

    @Test
    public void whenEntityHasCreatedDateThenReturnCreatedDate() {

        LocalDateTime createdDate = LocalDateTime.now();

        EmailEntity entity = new EmailEntity();
        entity.setCreatedDate(createdDate);

        Email email = EmailMapper.map(entity);

        Assertions.assertEquals(createdDate, email.getCreatedDate());
    }

    @Test
    public void whenEntityHasTemplateThenReturnTemplate() {

        EmailEntity entity = new EmailEntity();;
        entity.setTemplate(EmailTemplate.WELCOME);

        Email email = EmailMapper.map(entity);

        Assertions.assertEquals(EmailTemplate.WELCOME, email.getTemplate());
    }

    @Test
    public void whenEntityHasStatusThenReturnStatus() {

        EmailEntity entity = new EmailEntity();
        entity.setStatus(EmailStatus.SENT);

        Email email = EmailMapper.map(entity);

        Assertions.assertEquals(EmailStatus.SENT, email.getStatus());
    }

    @Test
    public void whenEntityHasToAddressThenReturnToAddress() {

        EmailEntity entity = new EmailEntity();
        entity.setToAddress("toAddress");

        Email email = EmailMapper.map(entity);

        Assertions.assertEquals("toAddress", email.getToAddress());
    }

    @Test
    public void whenEntityHasBodyContextThenReturnBodyContext() {

        Map<String, Object> map = new HashMap<>();
        map.put("test", "test");

        EmailEntity entity = new EmailEntity();
        entity.setBodyContext(map);

        Email email = EmailMapper.map(entity);

        Map<String, Object> expected = new HashMap<>();
        expected.put("test", "test");

        Assertions.assertEquals(expected, email.getBodyContext());
    }

    @Test
    public void whenEntityHasSubjectContextThenReturnSubjectContext() {

        EmailEntity entity = new EmailEntity();
        entity.setSubjectContext(Arrays.asList("test1", "test2"));

        Email email = EmailMapper.map(entity);

        Assertions.assertEquals(Arrays.asList("test1", "test2"), email.getSubjectContext());
    }

    @Test
    public void whenEntityHasSentDateThenExpectSentDate() {

        LocalDateTime sentDate = LocalDateTime.now();

        EmailEntity entity = new EmailEntity();
        entity.setSentDate(sentDate);

        Email email = EmailMapper.map(entity);

        Assertions.assertEquals(sentDate, email.getSentDate());
    }
}
