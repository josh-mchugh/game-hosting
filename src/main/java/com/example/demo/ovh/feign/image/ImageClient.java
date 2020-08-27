package com.example.demo.ovh.feign.image;

import com.example.demo.framework.feign.FeignOvhConfig;
import com.example.demo.ovh.feign.image.model.ImageApi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "imageClient", url = "${app.ovh.base-url}", configuration = FeignOvhConfig.class)
public interface ImageClient {

    @GetMapping("/1.0/cloud/project/{projectId}/image")
    List<ImageApi> getImages(@PathVariable("projectId") String projectId);

    @GetMapping("/1.0/cloud/project/{projectId}/image/{imageId}")
    ImageApi getImage(@PathVariable("projectId") String projectId, @PathVariable("imageId") String imageId);
}
