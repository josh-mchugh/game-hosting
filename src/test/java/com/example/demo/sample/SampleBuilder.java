package com.example.demo.sample;

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

    private User user;
    private Game game;
    private Region region;
    private Flavor flavor;
    private Image image;
    private Credential credential;
    private Project project;
    private InstanceGroup instanceGroup;
    private Instance instance;

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
                .build();
    }

    public class Builder {

        public Builder user() {

            user = createDefaultUser();

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
                    .build();
        }
    }

    private User createDefaultUser() {

        return userService.handleCreateUser(TestUserCreateRequest.createDefault());
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
}
