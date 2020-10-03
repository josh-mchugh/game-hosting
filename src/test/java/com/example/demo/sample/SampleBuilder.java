package com.example.demo.sample;

import com.example.demo.awx.credential.model.AwxCredential;
import com.example.demo.awx.credential.service.IAwxCredentialService;
import com.example.demo.awx.credential.service.model.AwxCredentialCreateRequest;
import com.example.demo.awx.host.model.AwxHost;
import com.example.demo.awx.host.service.IAwxHostService;
import com.example.demo.awx.host.service.model.AwxHostCreateRequest;
import com.example.demo.awx.inventory.model.AwxInventory;
import com.example.demo.awx.inventory.service.IAwxInventoryService;
import com.example.demo.awx.inventory.service.model.AwxInventoryCreateRequest;
import com.example.demo.awx.notification.model.AwxNotification;
import com.example.demo.awx.notification.service.IAwxNotificationService;
import com.example.demo.awx.notification.service.model.AwxNotificationCreateRequest;
import com.example.demo.awx.organization.model.AwxOrganization;
import com.example.demo.awx.organization.service.IAwxOrganizationService;
import com.example.demo.awx.organization.service.model.AwxOrganizationCreateRequest;
import com.example.demo.awx.playbook.aggregate.event.AwxPlaybookCreatedEvent;
import com.example.demo.awx.playbook.entity.PlaybookType;
import com.example.demo.awx.playbook.entity.model.AwxPlaybook;
import com.example.demo.awx.playbook.entity.service.IAwxPlaybookService;
import com.example.demo.awx.project.model.AwxProject;
import com.example.demo.awx.project.service.IAwxProjectService;
import com.example.demo.awx.project.service.model.AwxProjectCreateRequest;
import com.example.demo.awx.template.aggregate.event.AwxTemplateCreatedEvent;
import com.example.demo.awx.template.entity.TemplateJobType;
import com.example.demo.awx.template.entity.TemplateVerbosity;
import com.example.demo.awx.template.entity.model.AwxTemplate;
import com.example.demo.awx.template.entity.service.IAwxTemplateService;
import com.example.demo.game.model.Game;
import com.example.demo.game.service.IGameService;
import com.example.demo.ovh.credential.model.Credential;
import com.example.demo.ovh.credential.service.ICredentialService;
import com.example.demo.ovh.flavor.model.Flavor;
import com.example.demo.ovh.flavor.service.IFlavorService;
import com.example.demo.ovh.image.model.Image;
import com.example.demo.ovh.image.service.IImageService;
import com.example.demo.ovh.instance.model.Instance;
import com.example.demo.ovh.instance.model.InstanceGroup;
import com.example.demo.ovh.instance.service.IInstanceGroupService;
import com.example.demo.ovh.instance.service.IInstanceService;
import com.example.demo.ovh.instance.service.model.InstanceCreateRequest;
import com.example.demo.ovh.instance.service.model.InstanceGroupCreateRequest;
import com.example.demo.ovh.region.model.Region;
import com.example.demo.ovh.region.service.IRegionService;
import com.example.demo.project.model.Project;
import com.example.demo.project.service.IProjectService;
import com.example.demo.project.service.model.ProjectCreateRequest;
import com.example.demo.sample.util.TestAwxCredentialCreateRequest;
import com.example.demo.sample.util.TestAwxHostCreateRequest;
import com.example.demo.sample.util.TestAwxInventoryCreateRequest;
import com.example.demo.sample.util.TestAwxNotificationCreateRequest;
import com.example.demo.sample.util.TestAwxOrganizationCreateRequest;
import com.example.demo.sample.util.TestAwxProjectCreateRequest;
import com.example.demo.sample.util.TestCredentialCreateRequest;
import com.example.demo.sample.util.TestFlavorCreateRequest;
import com.example.demo.sample.util.TestGameCreateRequest;
import com.example.demo.sample.util.TestImageCreateRequest;
import com.example.demo.sample.util.TestInstanceCreateRequest;
import com.example.demo.sample.util.TestInstanceGroupCreateRequest;
import com.example.demo.sample.util.TestProjectCreateRequest;
import com.example.demo.sample.util.TestRegionCreateRequest;
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

        return gameService.handleGameCreateRequest(TestGameCreateRequest.createDefault());
    }

    private Region createDefaultRegion() {

        return regionService.handleRegionCreate(TestRegionCreateRequest.createDefault());
    }

    private Flavor createDefaultFlavor() {

        return flavorService.handleFlavorCreate(TestFlavorCreateRequest.createDefault());
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

        AwxOrganizationCreateRequest request = TestAwxOrganizationCreateRequest.createDefault();

        return awxOrganizationService.handleOrganizationCreate(request);
    }

    private AwxCredential createDefaultAwxCredential() {

        AwxCredentialCreateRequest request = TestAwxCredentialCreateRequest.builder()
                .awxOrganizationId(awxOrganization)
                .build();

        return awxCredentialService.handleAwxCredentialCreate(request);
    }

    private AwxCredential createGitlabCredential() {

        AwxCredentialCreateRequest request = TestAwxCredentialCreateRequest.builder(TestAwxCredentialCreateRequest.Type.GITLAB)
                .awxOrganizationId(awxOrganization)
                .build();

        return awxCredentialService.handleAwxCredentialCreate(request);
    }

    private AwxProject createDefaultAwxProject() {

        AwxProjectCreateRequest request = TestAwxProjectCreateRequest.builder()
                .awxOrganizationId(awxOrganization.getOrganizationId())
                .awxCredentialId(awxCredential.getCredentialId())
                .build();

        return awxProjectService.handleCreateRequest(request);
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

        AwxInventoryCreateRequest request = TestAwxInventoryCreateRequest.builder()
                .organization(awxOrganization)
                .build();

        return awxInventoryService.handleCreateRequest(request);
    }

    private AwxHost createDefaultAwxHost() {

        AwxHostCreateRequest request = TestAwxHostCreateRequest.builder()
                .instanceId(instance.getId())
                .inventoryId(awxInventory.getInventoryId())
                .build();

        return awxHostService.handleCreateRequest(request);
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

        AwxNotificationCreateRequest request = TestAwxNotificationCreateRequest.builder()
                .awxOrganization(awxOrganization)
                .build();

        return awxNotificationService.handleCreateNotification(request);
    }
}
