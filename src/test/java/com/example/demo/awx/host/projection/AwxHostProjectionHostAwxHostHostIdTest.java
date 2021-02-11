package com.example.demo.awx.host.projection;

import com.example.demo.awx.host.projection.model.AwxHostAwxIdProjection;
import com.example.demo.awx.host.projection.model.AwxHostAwxIdQuery;
import com.example.demo.sample.SampleBuilder;
import com.example.demo.sample.SampleData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class AwxHostProjectionHostAwxHostHostIdTest {

    @Autowired
    private IAwxHostProjector awxHostProjector;

    @Autowired
    private SampleBuilder sampleBuilder;

    @Test
    public void whenEntityExistsThenReturnProjection() {

        SampleData data = sampleBuilder.builder()
                .user()
                .region()
                .flavor()
                .image()
                .project()
                .credential()
                .instanceGroup()
                .instance()
                .awxOrganization()
                .awxInventory()
                .awxHost()
                .build();

        AwxHostAwxIdQuery query = new AwxHostAwxIdQuery(data.getInstance().getOvhId());

        AwxHostAwxIdProjection projection = awxHostProjector.getHostIdProjection(query);

        AwxHostAwxIdProjection expectedProject = new AwxHostAwxIdProjection(data.getAwxHost().getId().toString(), data.getAwxHost().getAwxId());

        Assertions.assertEquals(expectedProject, projection);
    }

    @Test
    public void whenEntityDoesNotExistsByInstanceIdThenReturnNull() {

        AwxHostAwxIdQuery query = new AwxHostAwxIdQuery("invalid instance id");

        Assertions.assertNull(awxHostProjector.getHostIdProjection(query));
    }

    @Test
    public void whenParamIsNullThenThrowException() {

        Assertions.assertThrows(NullPointerException.class, () -> awxHostProjector.getHostIdProjection(null));
    }
}
