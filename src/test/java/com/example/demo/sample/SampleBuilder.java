package com.example.demo.sample;

import com.example.demo.awx.credential.aggregate.event.AwxCredentialCreatedEvent;
import com.example.demo.awx.credential.entity.AwxCredentialType;
import com.example.demo.awx.credential.entity.model.AwxCredential;
import com.example.demo.awx.credential.entity.service.IAwxCredentialService;
import com.example.demo.awx.host.aggregate.event.AwxHostCreatedEvent;
import com.example.demo.awx.host.entity.model.AwxHost;
import com.example.demo.awx.host.entity.service.IAwxHostService;
import com.example.demo.awx.inventory.aggregate.event.AwxInventoryCreatedEvent;
import com.example.demo.awx.inventory.entity.model.AwxInventory;
import com.example.demo.awx.inventory.entity.service.IAwxInventoryService;
import com.example.demo.awx.notification.aggregate.event.AwxNotificationCreatedEvent;
import com.example.demo.awx.notification.entity.model.AwxNotification;
import com.example.demo.awx.notification.entity.service.IAwxNotificationService;
import com.example.demo.awx.organization.aggregate.event.AwxOrganizationCreatedEvent;
import com.example.demo.awx.organization.entity.model.AwxOrganization;
import com.example.demo.awx.organization.entity.service.IAwxOrganizationService;
import com.example.demo.awx.playbook.aggregate.event.AwxPlaybookCreatedEvent;
import com.example.demo.awx.playbook.entity.PlaybookType;
import com.example.demo.awx.playbook.entity.model.AwxPlaybook;
import com.example.demo.awx.playbook.entity.service.IAwxPlaybookService;
import com.example.demo.awx.project.aggregate.event.AwxProjectCreatedEvent;
import com.example.demo.awx.project.entity.model.AwxProject;
import com.example.demo.awx.project.entity.service.IAwxProjectService;
import com.example.demo.awx.template.aggregate.event.AwxTemplateCreatedEvent;
import com.example.demo.awx.template.entity.TemplateJobType;
import com.example.demo.awx.template.entity.TemplateVerbosity;
import com.example.demo.awx.template.entity.model.AwxTemplate;
import com.example.demo.awx.template.entity.service.IAwxTemplateService;
import com.example.demo.game.aggregate.event.GameCreatedEvent;
import com.example.demo.game.entity.GameType;
import com.example.demo.game.entity.model.Game;
import com.example.demo.game.entity.service.IGameService;
import com.example.demo.ovh.credential.model.Credential;
import com.example.demo.ovh.credential.service.ICredentialService;
import com.example.demo.ovh.flavor.aggregate.event.FlavorCreatedEvent;
import com.example.demo.ovh.flavor.entity.model.Flavor;
import com.example.demo.ovh.flavor.entity.service.IFlavorService;
import com.example.demo.ovh.image.model.Image;
import com.example.demo.ovh.image.service.IImageService;
import com.example.demo.ovh.instance.model.Instance;
import com.example.demo.ovh.instance.model.InstanceGroup;
import com.example.demo.ovh.instance.service.IInstanceGroupService;
import com.example.demo.ovh.instance.service.IInstanceService;
import com.example.demo.ovh.instance.service.model.InstanceCreateRequest;
import com.example.demo.ovh.instance.service.model.InstanceGroupCreateRequest;
import com.example.demo.ovh.region.aggregate.event.RegionCreatedEvent;
import com.example.demo.ovh.region.entity.RegionStatus;
import com.example.demo.ovh.region.entity.model.Region;
import com.example.demo.ovh.region.entity.service.IRegionService;
import com.example.demo.project.model.Project;
import com.example.demo.project.service.IProjectService;
import com.example.demo.project.service.model.ProjectCreateRequest;
import com.example.demo.sample.util.TestCredentialCreateRequest;
import com.example.demo.sample.util.TestImageCreateRequest;
import com.example.demo.sample.util.TestInstanceCreateRequest;
import com.example.demo.sample.util.TestInstanceGroupCreateRequest;
import com.example.demo.sample.util.TestProjectCreateRequest;
import com.example.demo.sample.util.TestUserCreateRequest;
import com.example.demo.user.model.User;
import com.example.demo.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SampleBuilder {

    @Autowired
    private IUserService userService;

    @Autowired
    private IGameService gameService;

    @Autowired
    private IRegionService regionService;

    @Autowired
    private IFlavorService flavorService;

    @Autowired
    private IImageService imageService;

    @Autowired
    private ICredentialService credentialService;

    @Autowired
    private IProjectService projectService;

    @Autowired
    private IInstanceGroupService instanceGroupService;

    @Autowired
    private IInstanceService instanceService;

    @Autowired
    private IAwxOrganizationService awxOrganizationService;

    @Autowired
    private IAwxCredentialService awxCredentialService;

    @Autowired
    private IAwxProjectService awxProjectService;

    @Autowired
    private IAwxPlaybookService awxPlaybookService;

    @Autowired
    private IAwxInventoryService awxInventoryService;

    @Autowired
    private IAwxHostService awxHostService;

    @Autowired
    private IAwxTemplateService awxTemplateService;

    @Autowired
    private IAwxNotificationService awxNotificationService;

    private User user;
    private Game game;
    private Region region;
    private Flavor flavor;
    private Image image;
    private Credential credential;
    private Project project;
    private InstanceGroup instanceGroup;
    private Instance instance;
    private AwxOrganization awxOrganization;
    private AwxCredential awxCredential;
    private AwxProject awxProject;
    private AwxPlaybook awxPlaybook;
    private AwxInventory awxInventory;
    private AwxHost awxHost;
    private AwxTemplate awxTemplate;
    private AwxNotification awxNotification;

    public Builder builder() {

        return new Builder();
    }

    public SampleData createDefault() {

        return this.builder()
                .user()
                .game()
                .region()
                .flavor()
                .image()
                .credential()
                .project()
                .instanceGroup()
                .instance()
                .awxOrganization()
                .awxCredential()
                .awxProject()
                .awxPlaybook()
                .awxInventory()
                .awxHost()
                .awxTemplate()
                .build();
    }

    public class Builder {

        public Builder user() {

            user = createDefaultUser();

            return this;
        }

        public Builder user(TestUserCreateRequest.Type type) {

            user = createUser(type);

            return this;
        }

        public Builder game() {

            game = createDefaultGame();

            return this;
        }

        public Builder region() {

            region = createDefaultRegion();

            return this;
        }

        public Builder flavor() {

            flavor = createDefaultFlavor();

            return this;
        }

        public Builder image() {

            image = createDefaultImage();

            return this;
        }

        public Builder credential() {

            credential = createDefaultCredential();

            return this;
        }

        public Builder project() {

            project = createDefaultProject();

            return this;
        }

        public Builder instanceGroup() {

            instanceGroup = createDefaultInstanceGroup();

            return this;
        }

        public Builder instance() {

            instance = createDefaultInstance();

            return this;
        }

        public Builder awxOrganization() {

            awxOrganization = createDefaultAwxOrganization();

            return this;
        }

        public Builder awxCredential() {

            awxCredential = createDefaultAwxCredential();

            return this;
        }

        public Builder awxGitlabCredential() {

            awxCredential = createGitlabCredential();

            return this;
        }

        public Builder awxProject() {

            awxProject = createDefaultAwxProject();

            return this;
        }

        public Builder awxPlaybook() {

            awxPlaybook = createDefaultAwxPlaybook();

            return this;
        }

        public Builder awxInventory() {

            awxInventory = createDefaultAwxInventory();

            return this;
        }

        public Builder awxHost() {

            awxHost = createDefaultAwxHost();

            return this;
        }

        public Builder awxTemplate() {

            awxTemplate = createDefaultAwxTemplate();

            return this;
        }

        public Builder awxNotification() {

            awxNotification = createDefaultAwxNotification();

            return this;
        }

        public SampleData build() {

            return SampleData.builder()
                    .user(user)
                    .game(game)
                    .region(region)
                    .flavor(flavor)
                    .image(image)
                    .credential(credential)
                    .project(project)
                    .instanceGroup(instanceGroup)
                    .instance(instance)
                    .awxOrganization(awxOrganization)
                    .awxCredential(awxCredential)
                    .awxProject(awxProject)
                    .awxPlaybook(awxPlaybook)
                    .awxInventory(awxInventory)
                    .awxHost(awxHost)
                    .awxTemplate(awxTemplate)
                    .awxNotification(awxNotification)
                    .build();
        }
    }

    private User createDefaultUser() {

        return userService.handleCreateUser(TestUserCreateRequest.createDefault());
    }

    private User createUser(TestUserCreateRequest.Type type) {

        return userService.handleCreateUser(TestUserCreateRequest.builder(type).build());
    }

    private Game createDefaultGame() {

        return gameService.handleCreated(new GameCreatedEvent(UUID.randomUUID(), GameType.MINECRAFT_JAVA));
    }

    private Region createDefaultRegion() {

        RegionCreatedEvent event = RegionCreatedEvent.builder()
                .id(UUID.randomUUID())
                .name("US-EAST-VA-1")
                .continentCode("US")
                .countryCodes("us")
                .dataCenterLocation("US-EAST-VA")
                .status(RegionStatus.UP)
                .build();

        return regionService.handleCreated(event);
    }

    private Flavor createDefaultFlavor() {

        FlavorCreatedEvent event = FlavorCreatedEvent.builder()
                .id(UUID.randomUUID())
                .flavorId("a64381e7-c4e7-4b01-9fbe-da405c544d2e")
                .regionId(region.getId())
                .name("s1-2")
                .type("ovh.vps-ssd")
                .available(true)
                .hourly("s1-2.consumption")
                .monthly("s1-2.monthly")
                .quota(3)
                .osType("linux")
                .vcpus(1)
                .ram(2000)
                .disk(10)
                .outboundBandwidth(100)
                .inboundBandwidth(100)
                .build();

        return flavorService.handleCreated(event);
    }

    private Image createDefaultImage() {

        return imageService.handleImageCreate(TestImageCreateRequest.createDefault());
    }

    private Credential createDefaultCredential() {

       return  credentialService.handleSshKeyCreate(TestCredentialCreateRequest.createDefault());
    }

    private Project createDefaultProject() {

        if (user == null) user = createDefaultUser();
        if (game == null) game = createDefaultGame();

        ProjectCreateRequest request = TestProjectCreateRequest.builder()
                .name("project name")
                .userId(user)
                .gameType(game)
                .build();

        return projectService.handleProjectCreate(request);
    }

    private InstanceGroup createDefaultInstanceGroup() {

        if (project == null) project = createDefaultProject();

        InstanceGroupCreateRequest request = TestInstanceGroupCreateRequest.builder()
                .projectId(project.getId())
                .instanceGroupId("instance-group-id")
                .name("instance group name")
                .build();

        return instanceGroupService.handleInstanceGroupCreate(request);
    }

    private Instance createDefaultInstance() {

        if (instanceGroup == null) instanceGroup = createDefaultInstanceGroup();

        InstanceCreateRequest instanceCreateRequest = TestInstanceCreateRequest.builder()
                .instanceId("instance-id")
                .groupId(instanceGroup.getGroupId())
                .name("instance name")
                .build();

        return instanceService.handleInstanceCreate(instanceCreateRequest);
    }

    private AwxOrganization createDefaultAwxOrganization() {

        AwxOrganizationCreatedEvent event = AwxOrganizationCreatedEvent.builder()
                .id(UUID.randomUUID())
                .organizationId(1L)
                .name("Game Hosting Service")
                .description("organization description")
                .build();

        return awxOrganizationService.handleCreated(event);
    }

    private AwxCredential createDefaultAwxCredential() {

        AwxCredentialCreatedEvent event = AwxCredentialCreatedEvent.builder()
                .id(UUID.randomUUID())
                .organizationId(awxOrganization.getOrganizationId())
                .credentialId(1L)
                .name("Ansible")
                .description("Ansible Credential")
                .privateKey("private key")
                .passphrase("passphrase")
                .type(AwxCredentialType.MACHINE)
                .build();

        return awxCredentialService.handleCreated(event);
    }

    private AwxCredential createGitlabCredential() {

        AwxCredentialCreatedEvent event = AwxCredentialCreatedEvent.builder()
                .id(UUID.randomUUID())
                .organizationId(awxOrganization.getOrganizationId())
                .credentialId(1L)
                .name("Gitlab SCM")
                .description("Gitlab SCM Credential")
                .privateKey("private key")
                .passphrase("passphrase")
                .type(AwxCredentialType.SOURCE_CONTROL)
                .build();

        return awxCredentialService.handleCreated(event);
    }

    private AwxProject createDefaultAwxProject() {

        AwxProjectCreatedEvent event = AwxProjectCreatedEvent.builder()
                .id(UUID.randomUUID())
                .organizationId(awxOrganization.getOrganizationId())
                .awxCredentialId(awxCredential.getId())
                .projectId(1L)
                .name("Game Hosting Project")
                .description("Game Hosting Project")
                .scmType("git")
                .scmBranch("master")
                .scmUrl("url")
                .build();

        return awxProjectService.handleCreated(event);
    }

    private AwxPlaybook createDefaultAwxPlaybook() {

        AwxPlaybookCreatedEvent event = AwxPlaybookCreatedEvent.builder()
                .id(UUID.randomUUID())
                .awxProjectId(awxProject.getId())
                .name("cowsay-playbook.yml")
                .type(PlaybookType.COWSAY)
                .build();

        return awxPlaybookService.handleAwxPlaybookCreated(event);
    }

    private AwxInventory createDefaultAwxInventory() {

        AwxInventoryCreatedEvent event = AwxInventoryCreatedEvent.builder()
                .id(UUID.randomUUID())
                .organizationId(awxOrganization.getOrganizationId())
                .inventoryId(1L)
                .name("Default")
                .description("Default Inventory")
                .build();

        return awxInventoryService.handleCreated(event);
    }

    private AwxHost createDefaultAwxHost() {

        AwxHostCreatedEvent event = AwxHostCreatedEvent.builder()
                .id(UUID.randomUUID())
                .awxInventoryId(awxInventory.getId())
                .instanceId(instance.getId())
                .hostId(1L)
                .hostname("hostname")
                .description("description")
                .enabled(true)
                .build();

        return awxHostService.handleCreated(event);
    }

    private AwxTemplate createDefaultAwxTemplate() {

        AwxTemplateCreatedEvent event = AwxTemplateCreatedEvent.builder()
                .id(UUID.randomUUID())
                .awxCredentialId(awxCredential.getId())
                .awxInventoryId(awxInventory.getId())
                .awxPlaybookId(awxPlaybook.getId())
                .templateId(1L)
                .name("run job")
                .description("runs a job")
                .jobType(TemplateJobType.RUN)
                .verbosity(TemplateVerbosity.NORMAL)
                .build();

        return awxTemplateService.handleAwxTemplateCreated(event);
    }

    private AwxNotification createDefaultAwxNotification() {

        AwxNotificationCreatedEvent event = AwxNotificationCreatedEvent.builder()
                .id(UUID.randomUUID())
                .organizationId(awxOrganization.getOrganizationId())
                .notificationId(1L)
                .name("name")
                .description("description")
                .organizationId(1L)
                .notificationType("notification type")
                .webhookCallBackUrl("callback url")
                .build();

        return awxNotificationService.handleCreated(event);
    }
}
