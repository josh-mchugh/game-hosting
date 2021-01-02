package com.example.demo.framework.properties;

import com.example.demo.ovh.credential.entity.CredentialType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;

public class OvhConfigTest {

    @Test
    public void whenConfigHasBaseUrlThenReturnBaseUrl() {

        OvhConfig config = new OvhConfig();
        config.setBaseUrl("baseUrl");

        Assertions.assertEquals("baseUrl", config.getBaseUrl());
    }

    @Test
    public void whenConfigHasAppKeyThenReturnAppKey() {

        OvhConfig config = new OvhConfig();
        config.setAppKey("appKey");

        Assertions.assertEquals("appKey", config.getAppKey());
    }

    @Test
    public void whenConfigHasAppSecretThenReturnAppSecret() {

        OvhConfig config = new OvhConfig();
        config.setAppSecret("appSecret");

        Assertions.assertEquals("appSecret", config.getAppSecret());
    }

    @Test
    public void whenConfigHasCustomerKeyThenReturnCustomerKey() {

        OvhConfig config = new OvhConfig();
        config.setCustomerKey("customerKey");

        Assertions.assertEquals("customerKey", config.getCustomerKey());
    }

    @Test
    public void whenConfigHasRegionSchedulerDelayThenReturnSchedulerDelay() {

        OvhConfig config = new OvhConfig();
        config.setRegionSchedulerDelay("regionSchedulerDelay");

        Assertions.assertEquals("regionSchedulerDelay", config.getRegionSchedulerDelay());
    }

    @Test
    public void whenConfigHasRegionSchedulerInitialDelayThenReturnSchedulerInitialDelay() {

        OvhConfig config = new OvhConfig();
        config.setRegionSchedulerInitialDelay("regionSchedulerInitialDelay");

        Assertions.assertEquals("regionSchedulerInitialDelay", config.getRegionSchedulerInitialDelay());
    }

    @Test
    public void whenConfigHasFlavorSchedulerDelayThenReturnFlavorSchedulerDelay() {

        OvhConfig config = new OvhConfig();
        config.setFlavorSchedulerDelay("flavorSchedulerDelay");

        Assertions.assertEquals("flavorSchedulerDelay", config.getFlavorSchedulerDelay());
    }

    @Test
    public void whenConfigHasFlavorSchedulerInitialDelayThenReturnFlavorSchedulerInitialDelay() {

        OvhConfig config = new OvhConfig();
        config.setFlavorSchedulerInitialDelay("flavorSchedulerInitialDelay");

        Assertions.assertEquals("flavorSchedulerInitialDelay", config.getFlavorSchedulerInitialDelay());
    }

    @Test
    public void whenConfigHasImageSchedulerDelayThenReturnImageSchedulerDelay() {

        OvhConfig config = new OvhConfig();
        config.setImageSchedulerDelay("imageSchedulerDelay");

        Assertions.assertEquals("imageSchedulerDelay", config.getImageSchedulerDelay());
    }

    @Test
    public void whenConfigHasImageSchedulerInitialDelayThenReturnImageSchedulerInitialDelay() {

        OvhConfig config = new OvhConfig();
        config.setImageSchedulerInitialDelay("imageSchedulerInitialDelay");

        Assertions.assertEquals("imageSchedulerInitialDelay", config.getImageSchedulerInitialDelay());
    }

    @Test
    public void whenConfigHasSshKeyConfigsThenReturnSshKeyConfigs() {

        OvhConfig config = new OvhConfig();
        config.setSshKeyConfigs(Collections.singletonList(new OvhConfig.SshKeyConfig()));

        Assertions.assertEquals(Collections.singletonList(new OvhConfig.SshKeyConfig()), config.getSshKeyConfigs());
    }

    @Test
    public void whenConfigToString() {

        OvhConfig config = config();

        String expected = "OvhConfig(baseUrl=baseUrl, appKey=appKey, appSecret=appSecret, customerKey=customerKey, projectId=projectId, regionSchedulerDelay=regionSchedulerDelay, regionSchedulerInitialDelay=regionSchedulerInitialDelay, flavorSchedulerDelay=flavorSchedulerDelay, flavorSchedulerInitialDelay=flavorSchedulerInitialDelay, imageSchedulerDelay=imageSchedulerDelay, imageSchedulerInitialDelay=imageSchedulerInitialDelay, sshKeyConfigs=[OvhConfig.SshKeyConfig(name=null, type=null, publicKey=null, privateKey=null)])";

        Assertions.assertEquals(expected, config.toString());
    }

    @Test
    public void whenConfigHashCode() {

        OvhConfig config = config();

        Assertions.assertEquals(493417953, config.hashCode());
    }

    @Test
    public void whenConfigEquals() {

        OvhConfig config1 = config();
        OvhConfig config2 = config();

        Assertions.assertEquals(config1, config2);
    }

    @Test
    public void whenConfigNotEquals() {

        OvhConfig config = config();

        Assertions.assertNotEquals(config, new OvhConfig());
    }

    @Test
    public void whenSshKeyHasNameThenReturnName() {

        OvhConfig.SshKeyConfig config = new OvhConfig.SshKeyConfig();
        config.setName("name");

        Assertions.assertEquals("name", config.getName());
    }

    @Test
    public void whenSshKeyHasTypeThenReturnType() {

        OvhConfig.SshKeyConfig config = new OvhConfig.SshKeyConfig();
        config.setType(CredentialType.ANSIBLE);

        Assertions.assertEquals(CredentialType.ANSIBLE, config.getType());
    }

    @Test
    public void whenSshKeyHasPublicKeyThenReturnPublicKey() {

        OvhConfig.SshKeyConfig config = new OvhConfig.SshKeyConfig();
        config.setPublicKey("publicKey");

        Assertions.assertEquals("publicKey", config.getPublicKey());
    }

    @Test
    public void whenSshKeyHasPrivateKeyThenReturnPrivateKey() {

        OvhConfig.SshKeyConfig config = new OvhConfig.SshKeyConfig();
        config.setPrivateKey("privateKey");

        Assertions.assertEquals("privateKey", config.getPrivateKey());
    }

    @Test
    public void whenSshKeyToString() {

        OvhConfig.SshKeyConfig config  = sshKey();

        String expected = "OvhConfig.SshKeyConfig(name=name, type=ANSIBLE, publicKey=publicKey, privateKey=privateKey)";

        Assertions.assertEquals(expected, config.toString());
    }

    @Test
    public void whenSshKeyHashCode() {

        OvhConfig.SshKeyConfig config = new OvhConfig.SshKeyConfig();
        config.setName("name");
        config.setPublicKey("publicKey");
        config.setPrivateKey("privateKey");

        Assertions.assertEquals(-997243437, config.hashCode());
    }

    @Test
    public void whenSshKeyEquals() {

        OvhConfig.SshKeyConfig config1 = sshKey();
        OvhConfig.SshKeyConfig config2 = sshKey();

        Assertions.assertEquals(config1, config2);
    }

    @Test
    public void whenSshKeyNotEquals() {

        OvhConfig.SshKeyConfig config = sshKey();

        Assertions.assertNotEquals(config, new OvhConfig.SshKeyConfig());
    }

    private OvhConfig config() {

        OvhConfig config = new OvhConfig();
        config.setBaseUrl("baseUrl");
        config.setAppKey("appKey");
        config.setAppSecret("appSecret");
        config.setCustomerKey("customerKey");
        config.setProjectId("projectId");
        config.setRegionSchedulerDelay("regionSchedulerDelay");
        config.setRegionSchedulerInitialDelay("regionSchedulerInitialDelay");
        config.setFlavorSchedulerDelay("flavorSchedulerDelay");
        config.setFlavorSchedulerInitialDelay("flavorSchedulerInitialDelay");
        config.setImageSchedulerDelay("imageSchedulerDelay");
        config.setImageSchedulerInitialDelay("imageSchedulerInitialDelay");
        config.setSshKeyConfigs(Collections.singletonList(new OvhConfig.SshKeyConfig()));

        return config;
    }

    private OvhConfig.SshKeyConfig sshKey() {

        OvhConfig.SshKeyConfig config = new OvhConfig.SshKeyConfig();
        config.setName("name");
        config.setType(CredentialType.ANSIBLE);
        config.setPublicKey("publicKey");
        config.setPrivateKey("privateKey");

        return config;
    }
}
