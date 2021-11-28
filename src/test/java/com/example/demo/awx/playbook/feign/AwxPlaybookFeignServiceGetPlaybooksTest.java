package com.example.demo.awx.playbook.feign;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

public class AwxPlaybookFeignServiceGetPlaybooksTest {

    private PlaybookClient playbookClient;

    @BeforeEach
    public void setup() {

        playbookClient = Mockito.mock(PlaybookClient.class);
    }

    @Test
    public void whenGetPlaybooksThenReturnList() {

        List<String> expected = Arrays.asList("test1", "test2");

        Mockito.when(playbookClient.getPlaybooks(Mockito.anyLong())).thenReturn(expected);

        PlaybookFeignServiceImpl service = new PlaybookFeignServiceImpl(playbookClient);

        Assertions.assertEquals(expected, service.getPlaybooks(1L));
    }
}
