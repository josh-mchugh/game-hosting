package com.example.demo.ovh.image.scheduler.service;

import com.example.demo.ovh.image.feign.model.ImageApi;
import com.example.demo.ovh.image.scheduler.service.model.ProcessedImagesResponse;
import com.google.common.collect.ImmutableList;

import java.util.concurrent.ExecutionException;

public interface ImageSchedulerService {

    ImmutableList<ImageApi> getImageResponses();

    ProcessedImagesResponse processScheduledImages(ImmutableList<ImageApi> imageResponses) throws ExecutionException, InterruptedException;
}
