package com.example.demo.sample;

import com.example.demo.ovh.flavor.service.model.FlavorCreateRequest;

public class TestFlavorUtil {

    public enum Type {
        S1_2,
        S1_4,
        S1_8
    }

    public static Builder builder() {

        return new Builder();
    }

    public static Builder builder(Type type) {

        return new Builder(type);
    }

    public static class Builder {

        private final FlavorCreateRequest.Builder builder;

        public Builder() {

            this.builder = getBuilderByType(Type.S1_2);
        }

        public Builder(Type type) {

            this.builder = getBuilderByType(type);
        }

        public Builder flavorId(String flavorId) {

            this.builder.flavorId(flavorId);

            return this;
        }

        public Builder regionName(String regionName) {

            this.builder.regionName(regionName);

            return this;
        }

        public FlavorCreateRequest build() {

            return builder.build();
        }
    }

    private static FlavorCreateRequest.Builder getBuilderByType(Type type) {

        switch (type) {
            case S1_4:
                return buildS1_4();
            case S1_8:
                return buildS1_8();
            default:
                return buildS1_2();
        }
    }

    private static FlavorCreateRequest.Builder buildS1_2() {

        return FlavorCreateRequest.builder()
                .flavorId("a64381e7-c4e7-4b01-9fbe-da405c544d2e")
                .regionName("US-EAST-VA-1")
                .name("s1-2")
                .type("ovh.vps-ssd")
                .available(true)
                .hourly("s1-2.consumption")
                .monthly("s1-2.monthly")
                .quota(3)
                .osType("linux")
                .vcpus(1)
                .ram(2000)
                .disk(10)
                .outboundBandwidth(100)
                .inboundBandwidth(100);
    }

    private static FlavorCreateRequest.Builder buildS1_4() {

        return FlavorCreateRequest.builder()
                .flavorId("41d119b1-a47e-44ed-b406-66c0c7538227")
                .regionName("US-EAST-VA-1")
                .name("s1-4")
                .type("ovh.vps-ssd")
                .available(true)
                .hourly("s1-4.consumption")
                .monthly("s1-4.monthly")
                .quota(3)
                .osType("linux")
                .vcpus(1)
                .ram(4000)
                .disk(20)
                .outboundBandwidth(100)
                .inboundBandwidth(100);
    }

    private static FlavorCreateRequest.Builder buildS1_8() {

        return FlavorCreateRequest.builder()
                .flavorId("d23f7fd6-a250-4600-bb95-bb4cd12d9a01")
                .regionName("US-EAST-VA-1")
                .name("s1-8")
                .type("ovh.vps-ssd")
                .available(true)
                .hourly("s1-8.consumption")
                .monthly("s1-8.monthly")
                .quota(3)
                .osType("linux")
                .vcpus(2)
                .ram(8000)
                .disk(40)
                .outboundBandwidth(100)
                .inboundBandwidth(100);
    }

    public static FlavorCreateRequest createFlavor(String flavorId, String regionName) {

        return FlavorCreateRequest.builder()
                .flavorId(flavorId)
                .regionName(regionName)
                .build();
    }
}
