package com.example.demo.sample;

import com.example.demo.ovh.instance.model.InstanceGroup;
import com.example.demo.ovh.instance.service.model.InstanceGroupCreateRequest;
import com.example.demo.project.model.Project;

public class TestInstanceGroupUtil {

    public enum Type {
        DEFAULT
    }

    public static Builder builder() {

        return new Builder();
    }

    public static class Builder {

        private final InstanceGroupCreateRequest.Builder builder;

        public Builder() {

            this.builder = getByType(Type.DEFAULT);
        }

        public Builder(Type type) {

            this.builder = getByType(type);
        }

        public Builder instanceGroupId(InstanceGroup instanceGroup) {

            return instanceGroupId(instanceGroup.getGroupId());
        }

        public Builder instanceGroupId(String instanceGroupId) {

            this.builder.instanceGroupId(instanceGroupId);

            return this;
        }

        public Builder name(String name) {

            this.builder.name(name);

            return this;
        }

        public Builder type(String type) {

            this.builder.type(type);

            return this;
        }

        public Builder projectId(Project project) {

            return this.projectId(project.getId());
        }

        public Builder projectId(String projectId) {

            this.builder.projectId(projectId);

            return this;
        }

        public InstanceGroupCreateRequest build() {

            return builder.build();
        }
    }

    private static InstanceGroupCreateRequest.Builder getByType(Type type) {

        switch (type) {
            default:
                return getDefault();
        }
    }

    /*
        {
            "id":"84ad0d1a-d65c-49a7-b6d4-516d1465a65e",
            "name":"test-group-1",
            "region":"US-EAST-VA-1",
            "type":"affinity",
            "instance_ids":[]
        }
     */
    private static InstanceGroupCreateRequest.Builder getDefault() {

        return InstanceGroupCreateRequest.builder()
                .instanceGroupId("84ad0d1a-d65c-49a7-b6d4-516d1465a65e")
                .name("test-group-1")
                .type("affinity");
    }
}
