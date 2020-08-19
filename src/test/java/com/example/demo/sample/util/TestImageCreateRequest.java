package com.example.demo.sample.util;

import com.example.demo.ovh.image.service.model.ImageCreateRequest;
import com.google.common.collect.ImmutableList;

import java.time.LocalDateTime;

public class TestImageCreateRequest {

    public enum Type {
        DEBIAN_8_GITLAB,
        UBUNTU_20_4
    }

    public static Builder builder() {

        return new Builder(Type.UBUNTU_20_4);
    }

    public static Builder builder(Type type) {

        return new Builder(type);
    }

    public static ImageCreateRequest createDefault() {

        return builder().build();
    }

    public static class Builder {

        private final ImageCreateRequest.Builder builder;

        public Builder(Type type) {

            this.builder = getImageByType(type);
        }

        public Builder imageId(String imageId) {

            builder.imageId(imageId);

            return this;
        }

        public Builder regionName(String regionName) {

            builder.regionName(regionName);

            return this;
        }

        public ImageCreateRequest build() {

            return builder.build();
        }
    }

    private static ImageCreateRequest.Builder getImageByType(Type type) {

        switch (type) {
            case DEBIAN_8_GITLAB:
                return debian8Gitlab();
            default:
                return ubuntu_20_04();
        }
    }

    private static ImageCreateRequest.Builder debian8Gitlab() {

        return ImageCreateRequest.builder()
                .name("Debian 8 - GitLab")
                .imageId("f3a43883-4d9c-45b8-8f7f-f310303e4eb1")
                .imageCreatedDate(LocalDateTime.of(2018, 8, 6, 18, 43, 4))
                .flavorType(null)
                .regionName("US-EAST-VA-1")
                .minDisk(0)
                .minRam(0)
                .monthly(null)
                .hourly(null)
                .type("linux")
                .username("debian")
                .status("active")
                .visibility("public");
    }

    private static ImageCreateRequest.Builder ubuntu_20_04() {

        return ImageCreateRequest.builder()
                .name("Ubuntu 20.04")
                .imageId("cefc8220-ba0a-4327-b13d-591abaf4be0c")
                .imageCreatedDate(LocalDateTime.of(2020, 4, 24, 9, 12, 57))
                .flavorType(null)
                .regionName("US-EAST-VA-1")
                .minDisk(0)
                .minRam(0)
                .monthly(null)
                .hourly(null)
                .type("linux")
                .username("ubuntu")
                .status("active")
                .visibility("public");
    }
}
