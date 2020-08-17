package com.example.demo.framework;

import com.example.demo.framework.properties.AppConfig;
import com.example.demo.game.entity.GameType;
import com.example.demo.game.model.Game;
import com.example.demo.game.service.IGameService;
import com.example.demo.game.service.model.GameCreateRequest;
import com.example.demo.ovh.credential.model.Credential;
import com.example.demo.ovh.credential.service.ICredentialService;
import com.example.demo.ovh.credential.service.model.CredentialCreateRequest;
import com.example.demo.ovh.feign.flavor.FlavorClient;
import com.example.demo.ovh.feign.flavor.model.FlavorApi;
import com.example.demo.ovh.feign.image.ImageClient;
import com.example.demo.ovh.feign.image.model.ImageApi;
import com.example.demo.ovh.feign.region.RegionClient;
import com.example.demo.ovh.feign.ssh.SshKeyClient;
import com.example.demo.ovh.feign.ssh.model.SshKeyApi;
import com.example.demo.ovh.feign.ssh.model.SshKeyCreateApi;
import com.example.demo.ovh.flavor.model.Flavor;
import com.example.demo.ovh.flavor.service.IFlavorService;
import com.example.demo.ovh.flavor.service.model.FlavorCreateRequest;
import com.example.demo.ovh.image.model.Image;
import com.example.demo.ovh.image.service.IImageService;
import com.example.demo.ovh.image.service.model.ImageCreateRequest;
import com.example.demo.ovh.region.model.Region;
import com.example.demo.ovh.region.service.IRegionService;
import com.example.demo.ovh.region.service.mapper.RegionCreateRequestMapper;
import com.example.demo.user.entity.UserState;
import com.example.demo.user.entity.UserType;
import com.example.demo.user.service.IUserService;
import com.example.demo.user.service.model.UserCreateRequest;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class SeedDataRunner {

    private final AppConfig appConfig;
    private final IUserService userService;
    private final RegionClient regionClient;
    private final ImageClient imageClient;
    private final FlavorClient flavorClient;
    private final SshKeyClient sshKeyClient;
    private final IRegionService regionService;
    private final IFlavorService flavorService;
    private final IImageService imageService;
    private final IGameService gameService;
    private final ICredentialService sshKeyService;

    @EventListener
    public void onContextRefreshedEvent(ContextRefreshedEvent event) {

        if(appConfig.isEnableSeedData()) {

            log.info("Initializing seed data...");

            if (!userService.existsUserByEmail(appConfig.getAdminUser().getUsername())) {

                log.info("Creating admin user: {}", "admin@admin.com");
                userService.handleCreateUser(createAdminUser());
            }

            if (!regionService.existsAny()) {

                log.info("Initializing Region entities");
                ImmutableList<Region> regions = createRegions();
                log.info("Regions initialized: {}", regions.size());
            }

            if (!flavorService.existsAny()) {

                log.info("Initializing Flavor entities");
                ImmutableList<Flavor> flavors = createFlavors();
                log.info("Flavors initialized: {}", flavors.size());
            }

            if (!imageService.existsAny()) {

                log.info("Initializing Image Entities");
                ImmutableList<Image> images = createImages();
                log.info("Images initialized: {}", images.size());
            }

            if (!gameService.existsAny()) {

                log.info("Initializing Game Entities");
                ImmutableList<Game> games = createGames();
                log.info("Games initialized: {}", games.size());
            }

            if (!sshKeyService.existsAny()) {

                log.info("Initializing default sshKeys");
                ImmutableList<Credential> credentials = createSshKeys();
                log.info("Ssh Keys Initialized: {}", credentials.size());
            }

            log.info("Initialization of seed data complete.");
        }
    }

    private UserCreateRequest createAdminUser() {

        return UserCreateRequest.builder()
                .email(appConfig.getAdminUser().getUsername())
                .password(appConfig.getAdminUser().getPassword())
                .state(UserState.ACTIVE)
                .type(UserType.ADMIN)
                .build();
    }

    private ImmutableList<Region> createRegions() {

        return regionClient.getRegions(appConfig.getOvh().getProjectId()).stream()
                .map(name -> regionClient.getRegion(appConfig.getOvh().getProjectId(), name))
                .map(RegionCreateRequestMapper::map)
                .map(regionService::handleRegionCreate)
                .collect(ImmutableList.toImmutableList());
    }

    private ImmutableList<Flavor> createFlavors() {

        return flavorClient.getFlavors(appConfig.getOvh().getProjectId()).stream()
                .map(this::buildFlavorCreateRequest)
                .map(flavorService::handleFlavorCreate)
                .collect(ImmutableList.toImmutableList());
    }

    private FlavorCreateRequest buildFlavorCreateRequest(FlavorApi flavor) {

        return FlavorCreateRequest.builder()
                .flavorId(flavor.getFlavorId())
                .name(flavor.getName())
                .type(flavor.getType())
                .regionName(flavor.getRegionName())
                .hourly(flavor.getPlanCodes().getHourly())
                .monthly(flavor.getPlanCodes().getMonthly())
                .osType(flavor.getOsType())
                .quota(flavor.getQuota())
                .available(flavor.isAvailable())
                .vcpus(flavor.getVcpus())
                .ram(flavor.getRam())
                .disk(flavor.getDisk())
                .inboundBandwidth(flavor.getInboundBandwidth())
                .outboundBandwidth(flavor.getOutboundBandwidth())
                .build();
    }

    private ImmutableList<Image> createImages() {

        return imageClient.getImages(appConfig.getOvh().getProjectId()).stream()
                .map(this::buildImageCreateRequest)
                .map(imageService::handleImageCreate)
                .collect(ImmutableList.toImmutableList());
    }

    private ImageCreateRequest buildImageCreateRequest(ImageApi response) {

        String hourly = response.getPlanCode() != null ? response.getPlanCode().getHourly() : null;
        String monthly = response.getPlanCode() != null ? response.getPlanCode().getHourly() : null;
        ImmutableList<String> tags = CollectionUtils.isNotEmpty(response.getTags()) ? ImmutableList.copyOf(response.getTags()) : null;

        return ImageCreateRequest.builder()
                .imageId(response.getImageId())
                .regionName(response.getRegionName())
                .name(response.getName())
                .type(response.getType())
                .imageCreatedDate(response.getCreationDate())
                .flavorType(response.getFlavorType())
                .hourly(hourly)
                .monthly(monthly)
                .size(response.getSize())
                .minRam(response.getMinRam())
                .minDisk(response.getMinDisk())
                .username(response.getUser())
                .status(response.getStatus())
                .visibility(response.getVisibility())
                .tags(tags)
                .build();
    }

    private ImmutableList<Game> createGames() {

        return Lists.newArrayList(GameType.values()).stream()
                .map(type -> GameCreateRequest.builder().type(type).build())
                .map(gameService::handleGameCreateRequest)
                .collect(ImmutableList.toImmutableList());
    }

    private SshKeyApi createSshKeyResponse(AppConfig.Ovh.SshKeyConfig config) {

        SshKeyCreateApi apiRequest = SshKeyCreateApi.builder()
                .name(config.getName())
                .publicKey(config.getPublicKey())
                .build();

        return sshKeyClient.createSshKey(appConfig.getOvh().getProjectId(), apiRequest);
    }

    private Credential createCredential(AppConfig.Ovh.SshKeyConfig config, SshKeyApi apiResponse) {

        CredentialCreateRequest request = CredentialCreateRequest.builder()
                .sshKeyId(apiResponse.getId())
                .name(apiResponse.getName())
                .publicKey(apiResponse.getPublicKey())
                .privateKey(config.getPrivateKey())
                .type(config.getType())
                .build();

        return sshKeyService.handleSshKeyCreate(request);
    }

    private ImmutableList<Credential> createSshKeys() {

        List<Credential> credentials = new ArrayList<>();

        List<SshKeyApi> apiResponses = sshKeyClient.getSshKeys(appConfig.getOvh().getProjectId());

        for(AppConfig.Ovh.SshKeyConfig config : appConfig.getOvh().getSshKeyConfigs()) {

            Optional<SshKeyApi> apiResponse = apiResponses.stream()
                    .filter(response -> response.getPublicKey().equals(config.getPublicKey()))
                    .findFirst();

            if(apiResponse.isPresent()) {

                credentials.add(createCredential(config, apiResponse.get()));

            } else {

                credentials.add(createCredential(config, createSshKeyResponse(config)));
            }
        }

        return ImmutableList.copyOf(credentials);
    }
}
