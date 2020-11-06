package com.example.demo.ovh.image.entity.service;

import com.example.demo.ovh.image.aggregate.event.ImageCreatedEvent;
import com.example.demo.ovh.image.aggregate.event.ImageUpdatedEvent;
import com.example.demo.ovh.image.entity.model.Image;

public interface IImageService {

    Image handleCreated(ImageCreatedEvent event);

    Image handleUpdated(ImageUpdatedEvent event);
}
