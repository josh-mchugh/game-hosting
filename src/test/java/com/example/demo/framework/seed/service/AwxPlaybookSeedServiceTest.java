package com.example.demo.framework.seed.service;

import com.example.demo.awx.feign.playbook.PlaybookClient;
import com.example.demo.awx.playbook.model.AwxPlaybook;
import com.example.demo.sample.SampleBuilder;
import com.google.common.collect.ImmutableList;
import feign.FeignException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.util.Collections;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class AwxPlaybookSeedServiceTest {

    @Autowired
    private AwxPlaybookSeedService awxPlaybookSeedService;

    @Autowired
    private SampleBuilder sampleBuilder;

    @MockBean
    private PlaybookClient playbookClient;

    @Test
    public void whenAwxPlaybooksExistsThenDataNotExistsReturnFalse() {

        sampleBuilder.builder()
                .awxOrganization()
                .awxGitlabCredential()
                .awxProject()
                .awxPlaybook()
                .build();

        Assertions.assertFalse(awxPlaybookSeedService.dataNotExists());
    }

    @Test
    public void whenAwxPlaybookDoesNotExistsThenDataNotExistsReturnTrue() {

        Assertions.assertTrue(awxPlaybookSeedService.dataNotExists());
    }

    @Test
    public void whenPlaybookApiReturnEmptyThenReturnEmptyArrayList() {

        sampleBuilder.builder()
                .awxOrganization()
                .awxGitlabCredential()
                .awxProject()
                .build();

        Mockito.when(playbookClient.getPlaybooks(Mockito.anyLong())).thenReturn(Collections.emptyList());

        ImmutableList<AwxPlaybook> awxPlaybooks = awxPlaybookSeedService.initializeData();

        Assertions.assertEquals(0, awxPlaybooks.size());
    }

    @Test
    public void whenPlaybookApiReturnValidListThenReturnArrayList() {

        sampleBuilder.builder()
                .awxOrganization()
                .awxGitlabCredential()
                .awxProject()
                .build();

        Mockito.when(playbookClient.getPlaybooks(Mockito.anyLong())).thenReturn(Collections.singletonList("cowsay-playbook.yml"));

        ImmutableList<AwxPlaybook> awxPlaybooks = awxPlaybookSeedService.initializeData();

        Assertions.assertEquals(1, awxPlaybooks.size());
    }

    @Test
    public void whenPlaybookApiReturnsInvalidPlaybookThenThrowException() {

        sampleBuilder.builder()
                .awxOrganization()
                .awxGitlabCredential()
                .awxProject()
                .build();

        Mockito.when(playbookClient.getPlaybooks(Mockito.anyLong())).thenReturn(Collections.singletonList("invalid playbook name"));

        Assertions.assertThrows(IllegalArgumentException.class, () -> awxPlaybookSeedService.initializeData());
    }

    @Test
    public void whenPlaybookApiReturnsIncorrectlyFormattedPlaybookThenThrowException() {

        sampleBuilder.builder()
                .awxOrganization()
                .awxGitlabCredential()
                .awxProject()
                .build();

        Mockito.when(playbookClient.getPlaybooks(Mockito.anyLong())).thenReturn(Collections.singletonList("invalid playbook name"));

        Assertions.assertThrows(IllegalArgumentException.class, () -> awxPlaybookSeedService.initializeData());
    }

    @Test
    public void whenPlayBookApiReturnsEmptyArrayThenThrowsErrorThenReturnEmptyArray() {

        sampleBuilder.builder()
                .awxOrganization()
                .awxGitlabCredential()
                .awxProject()
                .build();

        Mockito.when(playbookClient.getPlaybooks(Mockito.anyLong()))
                .thenReturn(Collections.emptyList())
                .thenThrow(FeignException.FeignClientException.class);

       ImmutableList<AwxPlaybook> awxPlaybooks = awxPlaybookSeedService.initializeData();

       Assertions.assertEquals(0, awxPlaybooks.size());
    }

    @Test
    public void whenTypeHasValueThenReturnValue() {

        Assertions.assertEquals("Awx Playbook", awxPlaybookSeedService.type());
    }

    @Test
    public void typeShouldNotBeNull() {

        Assertions.assertNotNull(awxPlaybookSeedService.type());
    }

    @Test
    public void whenOrderHasValueReturnValue() {

        Assertions.assertEquals(10, awxPlaybookSeedService.order());
    }

    @Test
    public void whenOrderShouldNotBeNull() {

        Assertions.assertNotNull(awxPlaybookSeedService.order());
    }
}
