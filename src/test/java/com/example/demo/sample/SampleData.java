package com.example.demo.sample;

import com.example.demo.awx.credential.entity.model.AwxCredential;
import com.example.demo.awx.host.entity.model.AwxHost;
import com.example.demo.awx.inventory.entity.model.AwxInventory;
import com.example.demo.awx.notification.entity.model.AwxNotification;
import com.example.demo.awx.organization.entity.model.AwxOrganization;
import com.example.demo.awx.playbook.entity.model.AwxPlaybook;
import com.example.demo.awx.project.entity.model.AwxProject;
import com.example.demo.awx.template.entity.model.AwxTemplate;
import com.example.demo.game.entity.model.Game;
import com.example.demo.game.entity.model.GameServer;
import com.example.demo.ovh.credential.entity.model.Credential;
import com.example.demo.ovh.flavor.entity.model.Flavor;
import com.example.demo.ovh.image.entity.model.Image;
import com.example.demo.ovh.instance.entity.model.Instance;
import com.example.demo.ovh.instance.entity.model.InstanceGroup;
import com.example.demo.ovh.region.entity.model.Region;
import com.example.demo.project.entity.model.Project;
import com.example.demo.user.entity.model.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(builderClassName = "Builder")
public class SampleData {

    private final User user;
    private final Game game;
    private final Region region;
    private final Flavor flavor;
    private final Image image;
    private final GameServer gameServer;
    private final Credential credential;
    private final Project project;
    private final InstanceGroup instanceGroup;
    private final Instance instance;
    private final AwxOrganization awxOrganization;
    private final AwxCredential awxCredential;
    private final AwxProject awxProject;
    private final AwxPlaybook awxPlaybook;
    private final AwxInventory awxInventory;
    private final AwxHost awxHost;
    private final AwxTemplate awxTemplate;
    private final AwxNotification awxNotification;
}
