package com.example.demo.util;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.web.util.UriComponentsBuilder;

@RequiredArgsConstructor
public class AppUrlUtil {

    private final String baseUrl;

    public String getAppUrl() {

        return UriComponentsBuilder.fromHttpUrl(baseUrl)
                .toUriString();
    }

    public String getAppUrl(String path) {

        return UriComponentsBuilder.fromHttpUrl(baseUrl)
            .path(path)
            .toUriString();
    }

    public String getAppUrl(String path, ImmutablePair<String, Object> param) {

        return UriComponentsBuilder.fromHttpUrl(baseUrl)
                .path(path)
                .queryParam(param.left, param.right)
                .toUriString();
    }
}
