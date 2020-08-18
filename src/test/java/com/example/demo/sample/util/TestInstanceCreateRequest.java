package com.example.demo.sample.util;

import com.example.demo.ovh.instance.entity.InstanceStatus;
import com.example.demo.ovh.instance.service.model.InstanceCreateRequest;

import java.time.LocalDateTime;

public class TestInstanceCreateRequest {

    public enum Type {
        US_EAST_VA_1_Ubuntu_20_04
    }

    public static Builder builder() {

        return new Builder();
    }

    public static Builder builder(Type type) {

        return new Builder(type);
    }

    public static class Builder {

        private final InstanceCreateRequest.Builder builder;

        public Builder() {

            this.builder = getInstanceByType(Type.US_EAST_VA_1_Ubuntu_20_04);
        }

        public Builder(Type type) {

            this.builder = getInstanceByType(type);
        }

        public Builder instanceId(String instanceId) {

            this.builder.instanceId(instanceId);

            return this;
        }

        public Builder flavorId(String flavorId) {

            this.builder.flavorId(flavorId);

            return this;
        }

        public Builder imageId(String imageId) {

            this.builder.imageId(imageId);

            return this;
        }

        public Builder groupId(String groupId) {

            this.builder.groupId(groupId);

            return this;
        }

        public Builder name(String name) {

            this.builder.name(name);

            return this;
        }

        public Builder instanceCreatedDate(LocalDateTime instanceCratedDate) {

            this.builder.instanceCreatedDate(instanceCratedDate);

            return this;
        }

        public Builder ip4Address(String ip4Address) {

            this.builder.ip4Address(ip4Address);

            return this;
        }

        public Builder ip6Address(String ip6Address) {

             this.builder.ip6Address(ip6Address);

             return this;
        }

        public Builder status(InstanceStatus status) {

            this.builder.status(status);

            return this;
        }

        public InstanceCreateRequest build() {

            return builder.build();
        }
    }

    private static InstanceCreateRequest.Builder getInstanceByType(Type type) {

        switch (type) {
            default:
                return getUsEastVa1Ubuntu20_04();
        }
    }

    private static InstanceCreateRequest.Builder getUsEastVa1Ubuntu20_04() {

        return InstanceCreateRequest.builder()
                .instanceId("when-handle-instance-created-then-not-null")
                .imageId("cefc8220-ba0a-4327-b13d-591abaf4be0c")
                .flavorId("a64381e7-c4e7-4b01-9fbe-da405c544d2e")
                .groupId("84ad0d1a-d65c-49a7-b6d4-516d1465a65e")
                .name("b0f983ce-966f-499b-b5b8-7f0c4d85fa96")
                .sshKeyId("ssh key id")
                .status(InstanceStatus.ACTIVE)
                .instanceCreatedDate(LocalDateTime.of(2020, 8, 2, 0, 10, 26))
                .ip4Address("0.0.0.0.0.0")
                .ip6Address("0.0.0.0.0.0");
    }
}
