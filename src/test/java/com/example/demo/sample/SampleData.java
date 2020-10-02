package com.example.demo.sample;

import com.example.demo.awx.credential.model.AwxCredential;
import com.example.demo.awx.host.model.AwxHost;
import com.example.demo.awx.inventory.model.AwxInventory;
import com.example.demo.awx.notification.model.AwxNotification;
import com.example.demo.awx.organization.model.AwxOrganization;
import com.example.demo.awx.playbook.entity.model.AwxPlaybook;
import com.example.demo.awx.project.model.AwxProject;
import com.example.demo.awx.template.model.AwxTemplate;
import com.example.demo.game.model.Game;
import com.example.demo.ovh.credential.model.Credential;
import com.example.demo.ovh.flavor.model.Flavor;
import com.example.demo.ovh.image.model.Image;
import com.example.demo.ovh.instance.model.Instance;
import com.example.demo.ovh.instance.model.InstanceGroup;
import com.example.demo.ovh.region.model.Region;
import com.example.demo.project.model.Project;
import com.example.demo.user.model.User;
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
