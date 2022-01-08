package com.example.demo.sample;

import com.example.demo.awx.credential.entity.AwxCredentialType;
import com.example.demo.awx.credential.entity.model.AwxCredential;
import com.example.demo.awx.credential.service.AwxCredentialService;
import com.example.demo.awx.credential.service.model.AwxCredentialCreateRequest;
import com.example.demo.awx.host.entity.model.AwxHost;
import com.example.demo.awx.host.service.AwxHostService;
import com.example.demo.awx.host.service.model.AwxHostCreateRequest;
import com.example.demo.awx.inventory.entity.model.AwxInventory;
import com.example.demo.awx.inventory.service.AwxInventoryService;
import com.example.demo.awx.inventory.service.model.AwxInventoryCreateRequest;
import com.example.demo.awx.notification.entity.model.AwxNotification;
import com.example.demo.awx.notification.service.AwxNotificationService;
import com.example.demo.awx.notification.service.model.AwxNotificationCreateRequest;
import com.example.demo.awx.organization.aggregate.event.AwxOrganizationCreatedEvent;
import com.example.demo.awx.organization.entity.model.AwxOrganization;
import com.example.demo.awx.organization.entity.service.AwxOrganizationService;
import com.example.demo.awx.playbook.aggregate.event.AwxPlaybookCreatedEvent;
import com.example.demo.awx.playbook.entity.PlaybookType;
import com.example.demo.awx.playbook.entity.model.AwxPlaybook;
import com.example.demo.awx.playbook.entity.service.AwxPlaybookService;
import com.example.demo.awx.project.aggregate.event.AwxProjectCreatedEvent;
import com.example.demo.awx.project.entity.model.AwxProject;
import com.example.demo.awx.project.entity.service.AwxProjectService;
import com.example.demo.awx.template.aggregate.event.AwxTemplateCreatedEvent;
import com.example.demo.awx.template.entity.TemplateJobType;
import com.example.demo.awx.template.entity.TemplateVerbosity;
import com.example.demo.awx.template.entity.model.AwxTemplate;
import com.example.demo.awx.template.entity.service.AwxTemplateService;
import com.example.demo.game.aggregate.event.GameCreatedEvent;
import com.example.demo.game.aggregate.event.GameServerCreatedEvent;
import com.example.demo.game.entity.GameServerStatus;
import com.example.demo.game.entity.GameType;
import com.example.demo.game.entity.model.Game;
import com.example.demo.game.entity.model.GameServer;
import com.example.demo.game.entity.service.GameServerService;
import com.example.demo.game.entity.service.GameService;
import com.example.demo.ovh.credential.aggregate.event.CredentialCreatedEvent;
import com.example.demo.ovh.credential.entity.CredentialType;
import com.example.demo.ovh.credential.entity.model.Credential;
import com.example.demo.ovh.credential.entity.service.CredentialService;
import com.example.demo.ovh.flavor.aggregate.event.FlavorCreatedEvent;
import com.example.demo.ovh.flavor.entity.model.Flavor;
import com.example.demo.ovh.flavor.entity.service.FlavorService;
import com.example.demo.ovh.image.aggregate.event.ImageCreatedEvent;
import com.example.demo.ovh.image.entity.model.Image;
import com.example.demo.ovh.image.entity.service.ImageService;
import com.example.demo.ovh.instance.aggregate.event.InstanceCreatedEvent;
import com.example.demo.ovh.instance.aggregate.event.InstanceGroupCreatedEvent;
import com.example.demo.ovh.instance.entity.InstanceStatus;
import com.example.demo.ovh.instance.entity.model.Instance;
import com.example.demo.ovh.instance.entity.model.InstanceGroup;
import com.example.demo.ovh.instance.entity.service.InstanceGroupService;
import com.example.demo.ovh.instance.entity.service.InstanceService;
import com.example.demo.ovh.region.aggregate.event.RegionCreatedEvent;
import com.example.demo.ovh.region.entity.RegionStatus;
import com.example.demo.ovh.region.entity.model.Region;
import com.example.demo.ovh.region.entity.service.RegionService;
import com.example.demo.project.aggregate.event.ProjectBillingAddedEvent;
import com.example.demo.project.aggregate.event.ProjectCreatedEvent;
import com.example.demo.project.aggregate.event.ProjectRegionAddedEvent;
import com.example.demo.project.aggregate.event.ProjectServerAddedEvent;
import com.example.demo.project.entity.ProjectMembershipRole;
import com.example.demo.project.entity.ProjectState;
import com.example.demo.project.entity.ProjectStatus;
import com.example.demo.project.entity.model.Project;
import com.example.demo.project.entity.service.ProjectService;
import com.example.demo.user.aggregate.event.UserCreatedEvent;
import com.example.demo.user.entity.UserState;
import com.example.demo.user.entity.UserType;
import com.example.demo.user.entity.model.User;
import com.example.demo.user.entity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SampleBuilder {

    @Autowired
    private UserService userService;

    @Autowired
    private GameService gameService;

    @Autowired
    private RegionService regionService;

    @Autowired
    private FlavorService flavorService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private GameServerService gameServerService;

    @Autowired
    private CredentialService credentialService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private InstanceGroupService instanceGroupService;

    @Autowired
    private InstanceService instanceService;

    @Autowired
    private AwxOrganizationService awxOrganizationService;

    @Autowired
    private AwxCredentialService awxCredentialService;

    @Autowired
    private AwxProjectService awxProjectService;

    @Autowired
    private AwxPlaybookService awxPlaybookService;

    @Autowired
    private AwxInventoryService awxInventoryService;

    @Autowired
    private AwxHostService awxHostService;

    @Autowired
    private AwxTemplateService awxTemplateService;

    @Autowired
    private AwxNotificationService awxNotificationService;

    private User user;
    private Game game;
    private Region region;
    private Flavor flavor;
    private Image image;
    private GameServer gameServer;
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
                .gameServer()
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

        public Builder adminUser() {

            user = createAdminUser();

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

        public Builder gameServer() {

            gameServer = createDefaultGameServer();

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

        public Builder configProjectRegion() {

            project = SampleBuilder.this.configProjectRegion();

            return this;
        }

        public Builder configProjectServer() {

            project = SampleBuilder.this.configProjectServer();

            return this;
        }

        public Builder configProjectBilling() {

            project = SampleBuilder.this.configProjectBilling();

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

        UserCreatedEvent event = UserCreatedEvent.builder()
                .id(UUID.randomUUID())
                .email("test@test")
                .password("password")
                .type(UserType.REGULAR)
                .state(UserState.ACTIVE)
                .verification(UserCreatedEvent.createVerification())
                .build();

        return userService.handleCreated(event);
    }

    private User createAdminUser() {

        UserCreatedEvent event = UserCreatedEvent.builder()
                .id(UUID.randomUUID())
                .email("admin@admin")
                .password("password")
                .type(UserType.ADMIN)
                .state(UserState.ACTIVE)
                .verification(UserCreatedEvent.createVerification())
                .build();

        return userService.handleCreated(event);
    }

    private Game createDefaultGame() {

        GameCreatedEvent event = GameCreatedEvent.builder()
                .id(UUID.randomUUID())
                .type(GameType.MINECRAFT_JAVA)
                .build();

        return gameService.handleCreated(event);
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

        if(region ==null) region = createDefaultRegion();

        FlavorCreatedEvent event = FlavorCreatedEvent.builder()
                .id(UUID.randomUUID())
                .ovhId("a64381e7-c4e7-4b01-9fbe-da405c544d2e")
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

        if(region == null) region = createDefaultRegion();

        ImageCreatedEvent event = ImageCreatedEvent.builder()
                .id(UUID.randomUUID())
                .name("Ubuntu 20.04")
                .ovhId("cefc8220-ba0a-4327-b13d-591abaf4be0c")
                .imageCreatedDate(LocalDateTime.of(2020, 4, 24, 9, 12, 57))
                .flavorType(null)
                .regionId(region.getId())
                .minDisk(0)
                .minRam(0)
                .monthly(null)
                .hourly(null)
                .type("linux")
                .username("ubuntu")
                .status("active")
                .visibility("public")
                .build();

        return imageService.handleCreated(event);
    }

    private GameServer createDefaultGameServer() {

        if (game == null) game = createDefaultGame();
        if (region == null) region = createDefaultRegion();
        if (flavor == null) flavor = createDefaultFlavor();
        if (image == null) image = createDefaultImage();

        GameServerCreatedEvent event = GameServerCreatedEvent.builder()
                .id(UUID.randomUUID())
                .name("name")
                .status(GameServerStatus.ACTIVE)
                .gameId(game.getId())
                .regionId(region.getId())
                .flavorId(flavor.getId())
                .imageId(image.getId())
                .build();

        return gameServerService.handleCreated(event);
    }

    private Credential createDefaultCredential() {

        CredentialCreatedEvent event = CredentialCreatedEvent.builder()
                .id(UUID.randomUUID())
                .ovhId("ovhId")
                .name("credential name")
                .publicKey("public key")
                .type(CredentialType.ANSIBLE)
                .build();

       return  credentialService.handleCreated(event);
    }

    private Project createDefaultProject() {

        if (user == null) user = createDefaultUser();
        if (game == null) game = createDefaultGame();

        ProjectCreatedEvent event = ProjectCreatedEvent.builder()
                .id(UUID.randomUUID())
                .name("project name")
                .status(ProjectStatus.CONFIG)
                .state(ProjectState.CONFIG_REGION)
                .gameId(game.getId())
                .member(ProjectCreatedEvent.createMember(user.getId(), ProjectMembershipRole.OWNER))
                .build();

        return projectService.handleCreated(event);
    }

    private Project configProjectRegion() {

        if (project == null) project = createDefaultProject();
        if (region == null) region = createDefaultRegion();

        ProjectRegionAddedEvent event = ProjectRegionAddedEvent.builder()
                .id(project.getId())
                .ovhRegionId(region.getId())
                .state(ProjectState.CONFIG_SERVER)
                .build();

        return projectService.handleRegionAdded(event);
    }

    private Project configProjectServer() {

        if (project == null) project = createDefaultProject();
        if (region == null) configProjectRegion();
        if (flavor == null) flavor = createDefaultFlavor();
        if (image == null) image = createDefaultImage();

        ProjectServerAddedEvent event = ProjectServerAddedEvent.builder()
                .id(project.getId())
                .ovhFlavorId(flavor.getId())
                .ovhImageId(image.getId())
                .state(ProjectState.CONFIG_BILLING)
                .build();

        return projectService.handleServerAdded(event);
    }

    private Project configProjectBilling() {

        if (project == null) project = createDefaultProject();
        if (region == null) configProjectRegion();
        if (flavor == null) configProjectServer();

        ProjectBillingAddedEvent event = ProjectBillingAddedEvent.builder()
                .id(project.getId())
                .status(ProjectStatus.BUILD)
                .state(ProjectState.BUILD_CREATE_INSTANCE_GROUP)
                .build();

        return projectService.handleBillingAdded(event);
    }

    private InstanceGroup createDefaultInstanceGroup() {

        if (project == null) project = createDefaultProject();

        InstanceGroupCreatedEvent event = InstanceGroupCreatedEvent.builder()
                .id(UUID.randomUUID())
                .projectId(project.getId())
                .ovhId("ovhId")
                .name("name")
                .type("type")
                .build();

        return instanceGroupService.handleCreated(event);
    }

    private Instance createDefaultInstance() {

        if (instanceGroup == null) instanceGroup = createDefaultInstanceGroup();
        if (flavor == null) flavor = createDefaultFlavor();
        if (image == null) image = createDefaultImage();
        if (credential == null) credential = createDefaultCredential();

        InstanceCreatedEvent event = InstanceCreatedEvent.builder()
                .id(UUID.randomUUID())
                .flavorId(flavor.getId())
                .imageId(image.getId())
                .credentialId(credential.getId())
                .instanceGroupId(instanceGroup.getId())
                .ovhId("ovhId")
                .name("name")
                .status(InstanceStatus.ACTIVE)
                .instanceCreatedDate(LocalDateTime.of(2020, 12, 23, 22, 30))
                .build();

        return instanceService.handleCreated(event);
    }

    private AwxOrganization createDefaultAwxOrganization() {

        AwxOrganizationCreatedEvent event = AwxOrganizationCreatedEvent.builder()
                .id(UUID.randomUUID())
                .awxId(1L)
                .name("Game Hosting Service")
                .description("organization description")
                .build();

        return awxOrganizationService.handleCreated(event);
    }

    private AwxCredential createDefaultAwxCredential() {

        AwxCredentialCreateRequest request = AwxCredentialCreateRequest.builder()
                .awxOrganizationId(awxOrganization.getId())
                .awxId(1L)
                .name("Ansible")
                .description("Ansible Credential")
                .privateKey("private key")
                .passphrase("passphrase")
                .type(AwxCredentialType.MACHINE)
                .build();

        return awxCredentialService.handleCreated(request);
    }

    private AwxCredential createGitlabCredential() {

        AwxCredentialCreateRequest request = AwxCredentialCreateRequest.builder()
                .awxOrganizationId(awxOrganization.getId())
                .awxId(1L)
                .name("Gitlab SCM")
                .description("Gitlab SCM Credential")
                .privateKey("private key")
                .passphrase("passphrase")
                .type(AwxCredentialType.SOURCE_CONTROL)
                .build();

        return awxCredentialService.handleCreated(request);
    }

    private AwxProject createDefaultAwxProject() {

        if(awxOrganization == null) awxOrganization = createDefaultAwxOrganization();
        if(awxCredential == null) awxCredential = createDefaultAwxCredential();

        AwxProjectCreatedEvent event = AwxProjectCreatedEvent.builder()
                .id(UUID.randomUUID())
                .awxOrganizationId(awxOrganization.getId())
                .awxCredentialId(awxCredential.getId())
                .awxId(1L)
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

        if(awxOrganization == null) awxOrganization = createDefaultAwxOrganization();

        AwxInventoryCreateRequest request = AwxInventoryCreateRequest.builder()
                .awxOrganizationId(awxOrganization.getId())
                .awxId(1L)
                .name("Default")
                .description("Default Inventory")
                .build();

        return awxInventoryService.handleCreate(request);
    }

    private AwxHost createDefaultAwxHost() {

        if(awxInventory == null) awxInventory = createDefaultAwxInventory();
        if(instance == null) instance = createDefaultInstance();

        AwxHostCreateRequest request = AwxHostCreateRequest.builder()
                .awxInventoryId(UUID.fromString(awxInventory.getId()))
                .instanceId(instance.getId())
                .awxId(1L)
                .hostname("hostname")
                .description("description")
                .enabled(true)
                .build();

        return awxHostService.handleCreate(request);
    }

    private AwxTemplate createDefaultAwxTemplate() {

        AwxTemplateCreatedEvent event = AwxTemplateCreatedEvent.builder()
                .id(UUID.randomUUID())
                .awxCredentialId(awxCredential.getId())
                .awxInventoryId(UUID.fromString(awxInventory.getId()))
                .awxPlaybookId(awxPlaybook.getId())
                .awxId(1L)
                .name("run job")
                .description("runs a job")
                .type(TemplateJobType.RUN)
                .verbosity(TemplateVerbosity.NORMAL)
                .build();

        return awxTemplateService.handleAwxTemplateCreated(event);
    }

    private AwxNotification createDefaultAwxNotification() {

        AwxNotificationCreateRequest request = AwxNotificationCreateRequest.builder()
                .awxOrganizationId(awxOrganization.getId())
                .awxId(1L)
                .name("name")
                .description("description")
                .type("type")
                .webhookCallBackUrl("callback url")
                .build();

        return awxNotificationService.handleCreated(request);
    }
}
