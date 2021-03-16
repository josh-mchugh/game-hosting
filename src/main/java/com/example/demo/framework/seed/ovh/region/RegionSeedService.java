package com.example.demo.framework.seed.ovh.region;

import com.example.demo.framework.seed.ISeedService;
import com.example.demo.framework.seed.ovh.region.projection.model.ExistsAnyRegionQuery;
import com.example.demo.framework.seed.ovh.region.projection.model.ExistsAnyRegionResponse;
import com.example.demo.ovh.region.aggregate.command.RegionCreateCommand;
import com.example.demo.ovh.region.feign.IRegionFeignService;
import com.example.demo.ovh.region.feign.model.RegionApi;
import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Component
@RequiredArgsConstructor
public class RegionSeedService implements ISeedService<Object> {

    private final IRegionFeignService regionFeignService;
    private final QueryGateway queryGateway;
    private final CommandGateway commandGateway;

    @Override
    public boolean dataNotExists() throws ExecutionException, InterruptedException {

        ExistsAnyRegionQuery query = new ExistsAnyRegionQuery();
        ExistsAnyRegionResponse response = queryGateway.query(query, ExistsAnyRegionResponse.class).get();

        return !response.exists();
    }

    @Override
    public ImmutableList<Object> initializeData() {

        return regionFeignService.getRegions().stream()
                .map(regionFeignService::getRegion)
                .map(this::createCommand)
                .map(commandGateway::sendAndWait)
                .collect(ImmutableList.toImmutableList());
    }

    @Override
    public String type() {

        return "Region";
    }

    @Override
    public Integer order() {

        return 2;
    }

    private RegionCreateCommand createCommand(RegionApi regionApi) {

        return RegionCreateCommand.builder()
                .id(UUID.randomUUID())
                .name(regionApi.getName())
                .continentCode(regionApi.getContinentCode())
                .countryCodes(joinCountryCodes(regionApi.getIpCountries()))
                .dataCenterLocation(regionApi.getDataCenterLocation())
                .status(regionApi.getStatus())
                .build();
    }

    private String joinCountryCodes(List<String> countryCodes) {

        if(CollectionUtils.isNotEmpty(countryCodes)) {

            return Joiner.on(",").join(countryCodes);
        }

        return null;
    }
}
