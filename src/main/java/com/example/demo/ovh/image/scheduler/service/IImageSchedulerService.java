package com.example.demo.ovh.image.scheduler.service;

import com.example.demo.ovh.feign.model.OvhImageApiResponse;
import com.example.demo.ovh.image.scheduler.service.model.ProcessedImagesResponse;
import com.google.common.collect.ImmutableList;

public interface IImageSchedulerService {

    ImmutableList<OvhImageApiResponse> getImageResponses();

    ProcessedImagesResponse processScheduledImages(ImmutableList<OvhImageApiResponse> imageResponses);
}
