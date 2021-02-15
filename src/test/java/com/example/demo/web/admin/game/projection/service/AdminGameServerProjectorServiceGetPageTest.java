package com.example.demo.web.admin.game.projection.service;

import com.example.demo.sample.SampleBuilder;
import com.example.demo.web.admin.game.projection.model.AdminGameServerPageRequest;
import com.example.demo.web.admin.game.projection.service.projection.AdminGameServerTableProjection;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
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

        Assertions.assertThrows(UndeclaredThrowableException.class, () -> service.getPage(null));
    }

    @Test
    public void whenParamHasNullPageableThenThrowException() {

        Assertions.assertThrows(UndeclaredThrowableException.class, () -> service.getPage(new AdminGameServerPageRequest(null)));
    }

    @Test
    public void whenParamHasValidPageableThenReturnResults() {

        sampleBuilder.builder()
                .gameServer()
                .build();

        Pageable pageable = PageRequest.of(0, 10);
        AdminGameServerPageRequest request = new AdminGameServerPageRequest(pageable);

        Page<AdminGameServerTableProjection> page = service.getPage(request);

        Assertions.assertTrue(CollectionUtils.isNotEmpty(page.getContent()));
    }

    @Test
    public void whenParamHasIsSortedByNameThenReturnSortedName() {

        sampleBuilder.builder()
                .gameServer()
                .build();

        Pageable pageable = PageRequest.of(0, 10, Sort.Direction.ASC, "name");
        AdminGameServerPageRequest request = new AdminGameServerPageRequest(pageable);

        Page<AdminGameServerTableProjection> page = service.getPage(request);

        Assertions.assertEquals("name", page.getSort().iterator().next().getProperty());
    }

    @Test
    public void whenParamHasIsSortedByGameTypeThenReturnSortedGameType() {

        sampleBuilder.builder()
                .gameServer()
                .build();

        Pageable pageable = PageRequest.of(0, 10, Sort.Direction.DESC, "gameType");
        AdminGameServerPageRequest request = new AdminGameServerPageRequest(pageable);

        Page<AdminGameServerTableProjection> page = service.getPage(request);

        Assertions.assertEquals("gameType", page.getSort().iterator().next().getProperty());
    }

    @Test
    public void whenParamHasIsSortedByRegionNameThenReturnSortedRegionName() {

        sampleBuilder.builder()
                .gameServer()
                .build();

        Pageable pageable = PageRequest.of(0, 10, Sort.Direction.ASC, "regionName");
        AdminGameServerPageRequest request = new AdminGameServerPageRequest(pageable);

        Page<AdminGameServerTableProjection> page = service.getPage(request);

        Assertions.assertEquals("regionName", page.getSort().iterator().next().getProperty());
    }

    @Test
    public void whenParamHasIsSortedByVcpusThenReturnSortedVcpus() {

        sampleBuilder.builder()
                .gameServer()
                .build();

        Pageable pageable = PageRequest.of(0, 10, Sort.Direction.DESC, "vcpus");
        AdminGameServerPageRequest request = new AdminGameServerPageRequest(pageable);

        Page<AdminGameServerTableProjection> page = service.getPage(request);

        Assertions.assertEquals("vcpus", page.getSort().iterator().next().getProperty());
    }

    @Test
    public void whenParamHasIsSortedByRamThenReturnSortedRam() {

        sampleBuilder.builder()
                .gameServer()
                .build();

        Pageable pageable = PageRequest.of(0, 10, Sort.Direction.ASC, "ram");
        AdminGameServerPageRequest request = new AdminGameServerPageRequest(pageable);

        Page<AdminGameServerTableProjection> page = service.getPage(request);

        Assertions.assertEquals("ram", page.getSort().iterator().next().getProperty());
    }

    @Test
    public void whenParamHasIsSortedByImageNameThenReturnSortedImageName() {

        sampleBuilder.builder()
                .gameServer()
                .build();

        Pageable pageable = PageRequest.of(0, 10, Sort.Direction.ASC, "imageName");
        AdminGameServerPageRequest request = new AdminGameServerPageRequest(pageable);

        Page<AdminGameServerTableProjection> page = service.getPage(request);

        Assertions.assertEquals("imageName", page.getSort().iterator().next().getProperty());
    }
}
