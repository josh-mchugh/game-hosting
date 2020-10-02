package com.example.demo.sample.util;

import com.example.demo.awx.credential.model.AwxCredential;
import com.example.demo.awx.inventory.model.AwxInventory;
import com.example.demo.awx.playbook.entity.PlaybookType;
import com.example.demo.awx.playbook.entity.model.AwxPlaybook;
import com.example.demo.awx.template.entity.TemplateJobType;
import com.example.demo.awx.template.entity.TemplateVerbosity;
import com.example.demo.awx.template.service.model.AwxTemplateCreateRequest;

public class TestAwxTemplateCreateRequest {

    public enum Type {
        DEFAULT
    }

    public static Builder builder() {

        return new Builder(Type.DEFAULT);
    }

    public static Builder builder(Type type) {

        return new Builder(type);
    }

    public static AwxTemplateCreateRequest createDefault() {

        return builder().build();
    }

    public static class Builder {

        private final AwxTemplateCreateRequest.Builder builder;

        public Builder(Type type) {

            builder = getByType(type);
        }

        public Builder credentialId(Long id) {

            builder.credentialId(id);

            return this;
        }

        public Builder awxCredential(AwxCredential awxCredential) {

            builder.credentialId(awxCredential.getCredentialId());

            return this;
        }

        public Builder inventoryId(Long id) {

            builder.inventoryId(id);

            return this;
        }

        public Builder awxInventory(AwxInventory awxInventory) {

            builder.inventoryId(awxInventory.getInventoryId());

            return this;
        }

        public Builder playbookType(PlaybookType playbookType) {

            builder.playbookType(playbookType);

            return this;
        }

        public Builder awxPlaybook(AwxPlaybook awxPlaybook) {

            builder.playbookType(awxPlaybook.getType());

            return this;
        }

        public Builder templateId(Long id) {

            builder.templateId(id);

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

        public Builder templateJobType(TemplateJobType jobType) {

            builder.jobType(jobType);

            return this;
        }

        public Builder verbosity(TemplateVerbosity verbosity) {

            builder.verbosity(verbosity);

            return this;
        }

        public AwxTemplateCreateRequest build() {

            return builder.build();
        }
    }

    private static AwxTemplateCreateRequest.Builder getByType(Type type) {

        switch (type) {
            default:
                return buildDefault();
        }
    }

    private static AwxTemplateCreateRequest.Builder buildDefault() {

        return AwxTemplateCreateRequest.builder()
                .credentialId(1L)
                .inventoryId(1L)
                .playbookType(PlaybookType.COWSAY)
                .templateId(1L)
                .name("run job")
                .description("runs a job")
                .jobType(TemplateJobType.RUN)
                .verbosity(TemplateVerbosity.NORMAL);
    }
}
