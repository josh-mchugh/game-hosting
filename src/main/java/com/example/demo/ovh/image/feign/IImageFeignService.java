package com.example.demo.ovh.image.feign;

import com.example.demo.ovh.image.feign.model.ImageApi;

import java.util.List;

public interface IImageFeignService {

    List<ImageApi> getImages();

    ImageApi getImage(String imageId);
}
