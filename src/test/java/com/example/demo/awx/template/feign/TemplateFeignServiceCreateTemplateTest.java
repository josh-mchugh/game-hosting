package com.example.demo.awx.template.feign;

import com.example.demo.awx.template.feign.model.TemplateApi;
import com.example.demo.awx.template.feign.model.TemplateCreateApi;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class TemplateFeignServiceCreateTemplateTest {

    @Test
    public void whenCreateTemplateThenReturnTemplateApi() {

        TemplateClient templateClient = Mockito.mock(TemplateClient.class);

        TemplateApi expected = new TemplateApi();

        Mockito.when(templateClient.createTemplate(Mockito.any())).thenReturn(expected);

        TemplateFeignServiceImpl service = new TemplateFeignServiceImpl(templateClient);

        Assertions.assertEquals(expected, service.createTemplate(TemplateCreateApi.builder().build()));
    }
}
