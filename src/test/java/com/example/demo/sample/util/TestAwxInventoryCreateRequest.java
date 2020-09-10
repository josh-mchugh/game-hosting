package com.example.demo.sample.util;

import com.example.demo.awx.inventory.service.model.AwxInventoryCreateRequest;
import com.example.demo.awx.organization.model.AwxOrganization;

public class TestAwxInventoryCreateRequest {

    public enum Type {
        DEFAULT
    }

    public static Builder builder() {

        return new Builder(Type.DEFAULT);
    }

    public static Builder builder(Type type) {

        return new Builder(type);
    }

    public static AwxInventoryCreateRequest createDefault() {

        return builder().build();
    }

    public static class Builder {

        private final AwxInventoryCreateRequest.Builder builder;

        public Builder(Type type) {

            this.builder = getByType(type);
        }

        public Builder organization(AwxOrganization awxOrganization) {

            builder.organizationId(awxOrganization.getOrganizationId());

            return this;
        }

        public Builder organizationId(Long id) {

            builder.organizationId(id);

            return this;
        }

        public Builder inventoryId(Long id) {

            builder.inventoryId(id);

            return this;
        }

        public Builder name(String name) {

            builder.name(name);

             return this;
        }

        public Builder description(String description) {

            builder.description(description);

            return this;
        }

        public AwxInventoryCreateRequest build() {

            return builder.build();
        }
    }

    private static AwxInventoryCreateRequest.Builder getByType(Type type) {

        switch (type) {
            default:
                return buildDefault();
        }
    }

    private static AwxInventoryCreateRequest.Builder buildDefault() {

        return AwxInventoryCreateRequest.builder()
                .organizationId(1L)
                .inventoryId(1L)
                .name("Default")
                .description("Default Inventory");
    }
}
