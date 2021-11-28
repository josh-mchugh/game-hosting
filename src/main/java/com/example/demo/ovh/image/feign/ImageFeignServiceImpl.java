package com.example.demo.ovh.image.feign;

import com.example.demo.framework.properties.OvhConfig;
import com.example.demo.ovh.image.feign.model.ImageApi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ImageFeignServiceImpl implements ImageFeignService {

    private final OvhConfig ovhConfig;
    private final ImageClient imageClient;

    @Override
    public List<ImageApi> getImages() {

        return imageClient.getImages(ovhConfig.getProjectId());
    }

    @Override
    public ImageApi getImage(String imageId) {

        return imageClient.getImage(ovhConfig.getProjectId(), imageId);
    }
}
