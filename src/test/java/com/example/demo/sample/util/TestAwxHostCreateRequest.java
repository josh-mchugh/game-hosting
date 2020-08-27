package com.example.demo.sample.util;

import com.example.demo.awx.host.service.model.AwxHostCreateRequest;
import com.example.demo.awx.inventory.model.AwxInventory;
import com.example.demo.ovh.instance.model.Instance;

public class TestAwxHostCreateRequest {

    public enum Type {
        DEFAULT
    }

    public static Builder builder() {

        return new Builder(Type.DEFAULT);
    }

    public static Builder builder(Type type) {

        return new Builder(type);
    }

    public static AwxHostCreateRequest createDefault() {

        return new Builder(Type.DEFAULT).build();
    }

    public static class Builder {

        private final AwxHostCreateRequest.Builder builder;

        public Builder(Type type) {

            builder = getByType(type);
        }

        public Builder inventory(AwxInventory awxInventory) {

            builder.inventoryId(awxInventory.getInventoryId());

            return this;
        }

        public Builder inventoryId(Long inventoryId) {

            builder.inventoryId(inventoryId);

            return this;
        }

        public Builder instance(Instance instance) {

            builder.instanceId(instance.getId());

            return this;
        }

        public Builder instanceId(String instanceId) {

            builder.instanceId(instanceId);

            return this;
        }

        public Builder hostname(String hostname) {

            builder.hostname(hostname);

            return this;
        }

        public Builder description(String description) {

            builder.description(description);

            return this;
        }

        public AwxHostCreateRequest build() {

            return builder.build();
        }
    }

    private static AwxHostCreateRequest.Builder getByType(Type type) {

        switch (type) {
            default:
                return buildDefault();
        }
    }

    private static AwxHostCreateRequest.Builder buildDefault() {

        return AwxHostCreateRequest.builder()
                .hostname("127.0.0.1")
                .description("This is home");
    }
}
