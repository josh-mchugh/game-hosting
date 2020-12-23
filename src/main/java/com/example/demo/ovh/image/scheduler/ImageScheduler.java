package com.example.demo.ovh.image.scheduler;

import com.example.demo.ovh.image.feign.model.ImageApi;
import com.example.demo.ovh.image.scheduler.service.IImageSchedulerService;
import com.example.demo.ovh.image.scheduler.service.model.ProcessedImagesResponse;
import com.google.common.collect.ImmutableList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Component
@RequiredArgsConstructor
public class ImageScheduler {

    private final IImageSchedulerService imageSchedulerService;

    @Scheduled(fixedDelayString = "${ovh.image-scheduler-delay}", initialDelayString = "${ovh.image-scheduler-initial-delay}")
    public void scheduledImageUpdater() {

        LocalDateTime startTime = LocalDateTime.now();

        ImmutableList<ImageApi> imageResponses = imageSchedulerService.getImageResponses();
        ProcessedImagesResponse response = imageSchedulerService.processScheduledImages(imageResponses);

        LocalDateTime endTime = LocalDateTime.now();

        log.info("Ovh Images | Stats - Total: {}, Created: {}, Updated: {} | Time - Start Time: {}, End Time: {}, Elapsed: {}ms",
                CollectionUtils.size(imageResponses),
                CollectionUtils.size(response.getCreatedImages()),
                CollectionUtils.size(response.getUpdatedImages()),
                startTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                endTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                Duration.between(startTime, endTime).toMillis()
        );
    }
}
