package com.example.demo.web.admin.game.projection.service;

import com.example.demo.sample.SampleBuilder;
import com.example.demo.web.admin.game.projection.service.model.FetchAdminGameServerTableQuery;
import com.example.demo.web.admin.game.projection.service.model.FetchAdminGameServerTableResponse;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.lang.reflect.UndeclaredThrowableException;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class AdminGameServerProjectorServiceGetPageTest {

    @Autowired
    private IAdminGameServerProjectorService service;

    @Autowired
    private SampleBuilder sampleBuilder;

    @Test
    public void whenParamIsNullThenThrowException() {

        Assertions.assertThrows(UndeclaredThrowableException.class, () -> service.getTable(null));
    }

    @Test
    public void whenParamHasNullPageableThenThrowException() {

        Assertions.assertThrows(UndeclaredThrowableException.class, () -> service.getTable(new FetchAdminGameServerTableQuery(null)));
    }

    @Test
    public void whenParamHasValidPageableThenReturnResults() {

        sampleBuilder.builder()
                .gameServer()
                .build();

        Pageable pageable = PageRequest.of(0, 10);
        FetchAdminGameServerTableQuery query = new FetchAdminGameServerTableQuery(pageable);

        FetchAdminGameServerTableResponse response = service.getTable(query);

        Assertions.assertTrue(CollectionUtils.isNotEmpty(response.getGameServers().getContent()));
    }

    @Test
    public void whenParamHasIsSortedByNameThenReturnSortedName() {

        sampleBuilder.builder()
                .gameServer()
                .build();

        Pageable pageable = PageRequest.of(0, 10, Sort.Direction.ASC, "name");
        FetchAdminGameServerTableQuery query = new FetchAdminGameServerTableQuery(pageable);

        FetchAdminGameServerTableResponse response = service.getTable(query);

        Assertions.assertEquals("name", response.getGameServers().getSort().iterator().next().getProperty());
    }

    @Test
    public void whenParamHasIsSortedByGameTypeThenReturnSortedGameType() {

        sampleBuilder.builder()
                .gameServer()
                .build();

        Pageable pageable = PageRequest.of(0, 10, Sort.Direction.DESC, "gameType");
        FetchAdminGameServerTableQuery query = new FetchAdminGameServerTableQuery(pageable);

        FetchAdminGameServerTableResponse response = service.getTable(query);

        Assertions.assertEquals("gameType", response.getGameServers().getSort().iterator().next().getProperty());
    }

    @Test
    public void whenParamHasIsSortedByRegionNameThenReturnSortedRegionName() {

        sampleBuilder.builder()
                .gameServer()
                .build();

        Pageable pageable = PageRequest.of(0, 10, Sort.Direction.ASC, "regionName");
        FetchAdminGameServerTableQuery query = new FetchAdminGameServerTableQuery(pageable);

        FetchAdminGameServerTableResponse response = service.getTable(query);

        Assertions.assertEquals("regionName", response.getGameServers().getSort().iterator().next().getProperty());
    }

    @Test
    public void whenParamHasIsSortedByVcpusThenReturnSortedVcpus() {

        sampleBuilder.builder()
                .gameServer()
                .build();

        Pageable pageable = PageRequest.of(0, 10, Sort.Direction.DESC, "vcpus");
        FetchAdminGameServerTableQuery query = new FetchAdminGameServerTableQuery(pageable);

        FetchAdminGameServerTableResponse response = service.getTable(query);

        Assertions.assertEquals("vcpus", response.getGameServers().getSort().iterator().next().getProperty());
    }

    @Test
    public void whenParamHasIsSortedByRamThenReturnSortedRam() {

        sampleBuilder.builder()
                .gameServer()
                .build();

        Pageable pageable = PageRequest.of(0, 10, Sort.Direction.ASC, "ram");
        FetchAdminGameServerTableQuery query = new FetchAdminGameServerTableQuery(pageable);

        FetchAdminGameServerTableResponse response = service.getTable(query);

        Assertions.assertEquals("ram", response.getGameServers().getSort().iterator().next().getProperty());
    }

    @Test
    public void whenParamHasIsSortedByImageNameThenReturnSortedImageName() {

        sampleBuilder.builder()
                .gameServer()
                .build();

        Pageable pageable = PageRequest.of(0, 10, Sort.Direction.ASC, "imageName");
        FetchAdminGameServerTableQuery query = new FetchAdminGameServerTableQuery(pageable);

        FetchAdminGameServerTableResponse response = service.getTable(query);

        Assertions.assertEquals("imageName", response.getGameServers().getSort().iterator().next().getProperty());
    }
}
