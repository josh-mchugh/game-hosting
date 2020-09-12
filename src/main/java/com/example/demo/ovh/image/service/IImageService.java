package com.example.demo.ovh.image.service;

import com.example.demo.ovh.image.model.Image;
import com.example.demo.ovh.image.service.model.ImageCreateRequest;
import com.example.demo.ovh.image.service.model.ImageUpdateRequest;

public interface IImageService {

    boolean existsAny();

    boolean existsByName(String name);

    Image handleImageCreate(ImageCreateRequest request);

    Image handleImageUpdate(ImageUpdateRequest request);
}
