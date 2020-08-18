package com.example.demo.sample.util;

import com.example.demo.ovh.region.entity.RegionStatus;
import com.example.demo.ovh.region.service.model.RegionCreateRequest;

public class TestRegionCreateRequest {

    public enum Type {
        US_EAST_VA_1,
        US_EAST_VA
    }

    public static Builder builder() {

        return new Builder();
    }

    public static Builder builder(Type type) {

        return new Builder(type);
    }

    public static RegionCreateRequest createDefault() {

        return builder().build();
    }

    public static class Builder {

        private final RegionCreateRequest.Builder builder;

        public Builder(Type type) {

            this.builder = getRegionByType(type);
        }

        public Builder() {

            this.builder = getRegionByType(Type.US_EAST_VA_1);
        }

        public Builder regionName(String regionName) {

            this.builder.name(regionName);

            return this;
        }

        public Builder status(RegionStatus status) {

            this.builder.status(status);

            return this;
        }

        public RegionCreateRequest build() {

            return builder.build();
        }
    }

    private static RegionCreateRequest.Builder getRegionByType(Type type) {

        if (type == Type.US_EAST_VA) {
            return usEastVa();
        }
        return usEastVa1();
    }

    private static RegionCreateRequest.Builder usEastVa() {

        return RegionCreateRequest.builder()
                .name("US-EAST-VA")
                .continentCode("US")
                .dataCenterLocation("US-EAST-VA")
                .status(RegionStatus.DOWN);
    }

    private static RegionCreateRequest.Builder usEastVa1() {

        return RegionCreateRequest.builder()
                .name("US-EAST-VA-1")
                .continentCode("US")
                .countryCodes("us")
                .dataCenterLocation("US-EAST-VA")
                .status(RegionStatus.UP);
    }
}
