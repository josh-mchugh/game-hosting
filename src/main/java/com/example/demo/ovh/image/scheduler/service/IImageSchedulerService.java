package com.example.demo.ovh.image.scheduler.service;

import com.example.demo.ovh.instance.feign.model.ImageApi;
import com.example.demo.ovh.image.scheduler.service.model.ProcessedImagesResponse;
import com.google.common.collect.ImmutableList;

public interface IImageSchedulerService {

    ImmutableList<ImageApi> getImageResponses();

    ProcessedImagesResponse processScheduledImages(ImmutableList<ImageApi> imageResponses);
}
