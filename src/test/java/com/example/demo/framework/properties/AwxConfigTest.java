package com.example.demo.framework.properties;

import com.example.demo.awx.credential.entity.AwxCredentialType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;

public class AwxConfigTest {

    @Test
    public void whenConfigHasBaseUrlThenReturnBaseUrl() {

        AwxConfig config = new AwxConfig();
        config.setBaseUrl("baseUrl");

        Assertions.assertEquals("baseUrl", config.getBaseUrl());
    }

    @Test
    public void whenConfigHasNotificationBaseUrlThenReturnNotificationBaseUrl() {

        AwxConfig config = new AwxConfig();
        config.setNotificationBaseUrl("notificationBaseUrl");

        Assertions.assertEquals("notificationBaseUrl", config.getNotificationBaseUrl());
    }

    @Test
    public void whenConfigHasUsernameThenReturnUsername() {

        AwxConfig config = new AwxConfig();
        config.setUsername("username");

        Assertions.assertEquals("username", config.getUsername());
    }

    @Test
    public void whenConfigHasPasswordThenReturnPassword() {

        AwxConfig config = new AwxConfig();
        config.setPassword("password");

        Assertions.assertEquals("password", config.getPassword());
    }

    @Test
    public void whenConfigHasOrganizationThenReturnOrganization() {

        AwxConfig config = new AwxConfig();
        config.setOrganization(new AwxConfig.Organization());

        Assertions.assertEquals(new AwxConfig.Organization(), config.getOrganization());
    }

    @Test
    public void whenConfigHasCredentialsThenReturnCredentials() {

        AwxConfig config = new AwxConfig();
        config.setCredentials(Collections.singletonList(new AwxConfig.Credential()));

        Assertions.assertEquals(Collections.singletonList(new AwxConfig.Credential()), config.getCredentials());
    }

    @Test
    public void whenConfigHasProjectThenReturnProject() {

        AwxConfig config = new AwxConfig();
        config.setProject(new AwxConfig.Project());

        Assertions.assertEquals(new AwxConfig.Project(), config.getProject());
    }

    @Test
    public void whenConfigHasInventoryThenReturnInventory() {

        AwxConfig config = new AwxConfig();
        config.setInventory(new AwxConfig.Inventory());

        Assertions.assertEquals(new AwxConfig.Inventory(), config.getInventory());
    }

    @Test
    public void whenConfigToString() {

        AwxConfig config = config();

        String expected = "AwxConfig(baseUrl=baseUrl, notificationBaseUrl=notificationBaseUrl, username=username, password=password, organization=AwxConfig.Organization(id=null), credentials=[AwxConfig.Credential(name=null, type=null, privateKey=null, passphrase=null)], project=AwxConfig.Project(name=null, description=null, scmType=null, scmUrl=null, scmBranch=null, credentialName=null), inventory=AwxConfig.Inventory(name=null, description=null))";

        Assertions.assertEquals(expected, config.toString());
    }

    @Test
    public void whenConfigHashCode() {

        AwxConfig config = config();

        Assertions.assertEquals(1841003265, config.hashCode());
    }

    @Test
    public void whenConfigEquals() {

        AwxConfig config1 = config();
        AwxConfig config2 = config();

        Assertions.assertEquals(config1, config2);
    }

    @Test
    public void whenConfigNotEquals() {

        AwxConfig config = config();

        Assertions.assertNotEquals(config, new AwxConfig());
    }

    @Test
    public void whenOrganizationHasIdThenReturnId() {

        AwxConfig.Organization organization = new AwxConfig.Organization();
        organization.setId(1L);

        Assertions.assertEquals(1L, organization.getId());
    }

    @Test
    public void whenOrganizationToString() {

        AwxConfig.Organization organization = organization();

        String expected = "AwxConfig.Organization(id=1)";

        Assertions.assertEquals(expected, organization.toString());
    }

    @Test
    public void whenOrganizationHashCode() {

        AwxConfig.Organization organization = organization();

        Assertions.assertEquals(60, organization.hashCode());
    }

    @Test
    public void whenOrganizationEquals() {

        AwxConfig.Organization organization1 = new AwxConfig.Organization();
        AwxConfig.Organization organization2 = new AwxConfig.Organization();

        Assertions.assertEquals(organization1, organization2);
    }

    @Test
    public void whenOrganizationNotEquals() {

        AwxConfig.Organization organization = organization();

        Assertions.assertNotEquals(organization, new AwxConfig.Organization());
    }

    @Test
    public void whenCredentialHasNamesThenReturnName() {

        AwxConfig.Credential credential = new AwxConfig.Credential();
        credential.setName("name");

        Assertions.assertEquals("name", credential.getName());
    }

    @Test
    public void whenCredentialHasTypeThenReturnType() {

        AwxConfig.Credential credential = new AwxConfig.Credential();
        credential.setType(AwxCredentialType.MACHINE);

        Assertions.assertEquals(AwxCredentialType.MACHINE, credential.getType());
    }

    @Test
    public void whenCredentialHasPrivateKeyThenReturnPrivateKey() {

        AwxConfig.Credential credential = new AwxConfig.Credential();
        credential.setPrivateKey("privateKey");

        Assertions.assertEquals("privateKey", credential.getPrivateKey());
    }

    @Test
    public void whenCredentialHasPassphraseThenReturnPassphrase() {

        AwxConfig.Credential credential = new AwxConfig.Credential();
        credential.setPassphrase("passphrase");

        Assertions.assertEquals("passphrase", credential.getPassphrase());
    }

    @Test
    public void whenCredentialToString() {

        AwxConfig.Credential credential = credential();

        String expected = "AwxConfig.Credential(name=name, type=MACHINE, privateKey=privateKey, passphrase=passphrase)";

        Assertions.assertEquals(expected, credential.toString());
    }

    @Test
    public void whenCredentialHashCode() {

        AwxConfig.Credential credential = new AwxConfig.Credential();
        credential.setName("name");
        credential.setPrivateKey("privateKey");
        credential.setPassphrase("passphrase");

        Assertions.assertEquals(-817563069, credential.hashCode());
    }

    @Test
    public void whenCredentialEquals() {

        AwxConfig.Credential credential1 = credential();
        AwxConfig.Credential credential2 = credential();

        Assertions.assertEquals(credential1, credential2);
    }

    @Test
    public void whenCredentialNotEquals() {

        AwxConfig.Credential credential = credential();

        Assertions.assertNotEquals(credential, new AwxConfig.Credential());
    }

    @Test
    public void whenProjectHasNameThenReturnName() {

        AwxConfig.Project project = new AwxConfig.Project();
        project.setName("name");

        Assertions.assertEquals("name", project.getName());
    }

    @Test
    public void whenProjectHasDescriptionThenReturnDescription() {

        AwxConfig.Project project = new AwxConfig.Project();
        project.setDescription("description");

        Assertions.assertEquals("description", project.getDescription());
    }

    @Test
    public void whenProjectHasScmTypeThenReturnScmType() {

        AwxConfig.Project project = new AwxConfig.Project();
        project.setScmType("scmType");

        Assertions.assertEquals("scmType", project.getScmType());
    }

    @Test
    public void whenProjectHasScmUrlThenReturnScmUrl() {

        AwxConfig.Project project = new AwxConfig.Project();
        project.setScmUrl("scmUrl");

        Assertions.assertEquals("scmUrl", project.getScmUrl());
    }

    @Test
    public void whenProjectHasScmBranchThenReturnScmBranch() {

        AwxConfig.Project project = new AwxConfig.Project();
        project.setScmBranch("scmBranch");

        Assertions.assertEquals("scmBranch", project.getScmBranch());
    }

    @Test
    public void whenProjectHasCredentialNameThenReturnCredentialName() {

        AwxConfig.Project project = new AwxConfig.Project();
        project.setCredentialName("credentialName");

        Assertions.assertEquals("credentialName", project.getCredentialName());
    }

    @Test
    public void whenProjectToString() {

        AwxConfig.Project project = project();

        String expected = "AwxConfig.Project(name=name, description=description, scmType=scmType, scmUrl=scmUrl, scmBranch=scmBranch, credentialName=credentialName)";

        Assertions.assertEquals(expected, project.toString());
    }

    @Test
    public void whenProjectHashCode() {

        AwxConfig.Project project = project();

        Assertions.assertEquals(1457634508, project.hashCode());
    }

    @Test
    public void whenProjectEquals() {

        AwxConfig.Project project1 = project();
        AwxConfig.Project project2 = project();

        Assertions.assertEquals(project1, project2);
    }

    @Test
    public void whenProjectNotEquals() {

        AwxConfig.Project project = project();

        Assertions.assertNotEquals(project, new AwxConfig.Project());
    }

    @Test
    public void whenInventoryHasNameThenReturnName() {

        AwxConfig.Inventory inventory = new AwxConfig.Inventory();
        inventory.setName("name");

        Assertions.assertEquals("name", inventory.getName());
    }

    @Test
    public void whenInventoryHasDescriptionThenReturnDescription() {

        AwxConfig.Inventory inventory = new AwxConfig.Inventory();
        inventory.setDescription("description");

        Assertions.assertEquals("description", inventory.getDescription());
    }

    @Test
    public void whenInventoryToString() {

        AwxConfig.Inventory inventory = inventory();

        String expected = "AwxConfig.Inventory(name=name, description=description)";

        Assertions.assertEquals(expected, inventory.toString());
    }

    @Test
    public void whenInventoryHashCode() {

        AwxConfig.Inventory inventory = inventory();

        Assertions.assertEquals(-1525493858, inventory.hashCode());
    }

    @Test
    public void whenInventoryEquals() {

        AwxConfig.Inventory inventory1 = inventory();
        AwxConfig.Inventory inventory2 = inventory();

        Assertions.assertEquals(inventory1, inventory2);
    }

    @Test
    public void whenInventoryNotEquals() {

        AwxConfig.Inventory inventory = inventory();

        Assertions.assertNotEquals(inventory, new AwxConfig.Inventory());
    }

    private AwxConfig config() {

        AwxConfig config = new AwxConfig();
        config.setBaseUrl("baseUrl");
        config.setNotificationBaseUrl("notificationBaseUrl");
        config.setUsername("username");
        config.setPassword("password");
        config.setOrganization(new AwxConfig.Organization());
        config.setCredentials(Collections.singletonList(new AwxConfig.Credential()));
        config.setProject(new AwxConfig.Project());
        config.setInventory(new AwxConfig.Inventory());

        return config;
    }

    private AwxConfig.Organization organization() {

        AwxConfig.Organization organization = new AwxConfig.Organization();
        organization.setId(1L);

        return organization;
    }

    private AwxConfig.Credential credential() {

        AwxConfig.Credential credential = new AwxConfig.Credential();
        credential.setName("name");
        credential.setType(AwxCredentialType.MACHINE);
        credential.setPrivateKey("privateKey");
        credential.setPassphrase("passphrase");

        return credential;
    }

    private AwxConfig.Project project() {

        AwxConfig.Project project = new AwxConfig.Project();
        project.setName("name");
        project.setDescription("description");
        project.setScmType("scmType");
        project.setScmUrl("scmUrl");
        project.setScmBranch("scmBranch");
        project.setCredentialName("credentialName");

        return project;
    }

    private AwxConfig.Inventory inventory() {

        AwxConfig.Inventory inventory = new AwxConfig.Inventory();
        inventory.setName("name");
        inventory.setDescription("description");

        return inventory;
    }
}
