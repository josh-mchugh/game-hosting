package com.example.demo.ovh.image.scheduler;

import com.example.demo.ovh.instance.feign.model.ImageApi;
import com.example.demo.ovh.image.scheduler.service.IImageSchedulerService;
import com.example.demo.ovh.image.scheduler.service.model.ProcessedImagesResponse;
import com.google.common.collect.ImmutableList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ImageScheduler {

    private final IImageSchedulerService imageSchedulerService;

    @Scheduled(fixedDelayString = "${ovh.image-scheduler-delay}", initialDelayString = "${ovh.image-scheduler-initial-delay}")
    public void scheduledImageUpdater() {

        log.info("Start: Image Updates");

        ImmutableList<ImageApi> imageResponses = imageSchedulerService.getImageResponses();

        log.info("Retrieved Images: {}", CollectionUtils.size(imageResponses));

        ProcessedImagesResponse response = imageSchedulerService.processScheduledImages(imageResponses);

        log.info("Created Images: {}", CollectionUtils.size(response.getCreatedImages()));
        log.info("Updated Images: {}", CollectionUtils.size(response.getUpdatedImages()));

        log.info("Finished: Image Updates");
    }
}
