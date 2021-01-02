package com.example.demo.framework.properties;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AppConfigTest {

    @Test
    public void whenConfigHasUrlThenReturnUrl() {

        AppConfig config = new AppConfig();
        config.setUrl("url");

        Assertions.assertEquals("url", config.getUrl());
    }

    @Test
    public void whenConfigHasEnableSeedDataThenReturnEnableSeedData() {

        AppConfig config = new AppConfig();
        config.setEnableSeedData(true);

        Assertions.assertTrue(config.isEnableSeedData());
    }

    @Test
    public void whenConfigHasAdminUserThenReturnAdminUser() {

        AppConfig config = new AppConfig();
        config.setAdminUser(new AppConfig.AdminUser());

        Assertions.assertEquals(new AppConfig.AdminUser(), config.getAdminUser());
    }

    @Test
    public void whenConfigHasEmailThenReturnEmail() {

        AppConfig config = new AppConfig();
        config.setEmail(new AppConfig.Email());

        Assertions.assertEquals(new AppConfig.Email(), config.getEmail());
    }

    @Test
    public void whenConfigHasPasswordThenReturnPassword() {

        AppConfig config = new AppConfig();
        config.setPassword(new AppConfig.Password());

        Assertions.assertEquals(new AppConfig.Password(), config.getPassword());
    }

    @Test
    public void whenConfigToString() {

        AppConfig config = config();

        String expected = "AppConfig(url=url, enableSeedData=true, adminUser=AppConfig.AdminUser(username=null, password=null), email=AppConfig.Email(noReplyAddress=null, supportAddress=null, schedulerDelay=null, schedulerInitialDelay=null, pagingSize=null), password=AppConfig.Password(recoverySchedulerDelay=null, recoverySchedulerInitialDelay=null, recoveryExpirationOffset=null))";

        Assertions.assertEquals(expected, config.toString());
    }

    @Test
    public void whenConfigHashCode() {

        AppConfig config = config();

        Assertions.assertEquals(-987655840, config.hashCode());
    }

    @Test
    public void whenConfigEquals() {

        AppConfig config1 = config();
        AppConfig config2 = config();

        Assertions.assertEquals(config1, config2);
    }

    @Test
    public void whenConfigNotEquals() {

        AppConfig config = config();

        Assertions.assertNotEquals(config, new AppConfig());
    }

    @Test
    public void whenAdminUserHasUsernameThenReturnUsername() {

        AppConfig.AdminUser adminUser = new AppConfig.AdminUser();
        adminUser.setUsername("username");

        Assertions.assertEquals("username", adminUser.getUsername());
    }

    @Test
    public void whenAdminUserHasPasswordThenReturnPassword() {

        AppConfig.AdminUser adminUser = new AppConfig.AdminUser();
        adminUser.setPassword("password");

        Assertions.assertEquals("password", adminUser.getPassword());
    }

    @Test
    public void whenAdminUserToString() {

        AppConfig.AdminUser adminUser = adminUser();

        String expected = "AppConfig.AdminUser(username=username, password=password)";

        Assertions.assertEquals(expected, adminUser.toString());
    }

    @Test
    public void whenAdminUserHashCode() {

        AppConfig.AdminUser adminUser = adminUser();

        Assertions.assertEquals(-1575202426, adminUser.hashCode());
    }

    @Test
    public void whenAdminUserEquals() {

        AppConfig.AdminUser adminUser1 = adminUser();
        AppConfig.AdminUser adminUser2 = adminUser();

        Assertions.assertEquals(adminUser1, adminUser2);
    }

    @Test
    public void whenAdminUserNotEquals() {

        AppConfig.AdminUser adminUser = adminUser();

        Assertions.assertNotEquals(adminUser, new AppConfig.AdminUser());
    }

    @Test
    public void whenEmailHasNoReplyAddressThenReturnNoReplyAddress() {

        AppConfig.Email email = new AppConfig.Email();
        email.setNoReplyAddress("noReplyAddress");

        Assertions.assertEquals("noReplyAddress", email.getNoReplyAddress());
    }

    @Test
    public void whenEmailHasSupportAddressThenReturnSupportAddress() {

        AppConfig.Email email = new AppConfig.Email();
        email.setSupportAddress("supportAddress");

        Assertions.assertEquals("supportAddress", email.getSupportAddress());
    }

    @Test
    public void whenEmailHasSchedulerDelayThenReturnSchedulerDelay() {

        AppConfig.Email email = new AppConfig.Email();
        email.setSchedulerDelay("schedulerDelay");

        Assertions.assertEquals("schedulerDelay", email.getSchedulerDelay());
    }

    @Test
    public void whenEmailHasSchedulerInitialDelayThenReturnSchedulerInitialDelay() {

        AppConfig.Email email = new AppConfig.Email();
        email.setSchedulerInitialDelay("schedulerInitialDelay");

        Assertions.assertEquals("schedulerInitialDelay", email.getSchedulerInitialDelay());
    }

    @Test
    public void whenEmailHasPagingSizeThenReturnPagingSize() {

        AppConfig.Email email = new AppConfig.Email();
        email.setPagingSize(1);

        Assertions.assertEquals(1, email.getPagingSize());
    }

    @Test
    public void whenEmailToString() {

        AppConfig.Email email = email();

        String expected = "AppConfig.Email(noReplyAddress=noReplyAddress, supportAddress=supportAddress, schedulerDelay=schedulerDelay, schedulerInitialDelay=schedulerInitialDelay, pagingSize=1)";

        Assertions.assertEquals(expected, email.toString());
    }

    @Test
    public void whenEmailHashCode() {

        AppConfig.Email email = email();

        Assertions.assertEquals(1088083324, email.hashCode());
    }

    @Test
    public void whenEmailEquals() {

        AppConfig.Email email1 = email();
        AppConfig.Email email2 = email();

        Assertions.assertEquals(email1, email2);
    }

    @Test
    public void whenEmailNotEquals() {

        AppConfig.Email email = email();

        Assertions.assertNotEquals(email, new AppConfig.Email());
    }

    @Test
    public void whenPasswordHasRecoverySchedulerInitialDelayThenReturnRecoverySchedulerInitialDelay() {

        AppConfig.Password password = new AppConfig.Password();
        password.setRecoverySchedulerInitialDelay("recoverySchedulerInitialDelay");

        Assertions.assertEquals("recoverySchedulerInitialDelay", password.getRecoverySchedulerInitialDelay());
    }

    @Test
    public void whenPasswordHasRecoverySchedulerDelayThenReturnRecoverySchedulerDelay() {

        AppConfig.Password password = new AppConfig.Password();
        password.setRecoverySchedulerDelay("recoverySchedulerDelay");

        Assertions.assertEquals("recoverySchedulerDelay", password.getRecoverySchedulerDelay());
    }


    @Test
    public void whenPasswordHasRecoveryExpirationOffsetThenReturnRecoveryExpirationOffset() {

        AppConfig.Password password = new AppConfig.Password();
        password.setRecoveryExpirationOffset(1L);

        Assertions.assertEquals(1L, password.getRecoveryExpirationOffset());
    }

    @Test
    public void whenPasswordToString() {

        AppConfig.Password password = password();

        String expected = "AppConfig.Password(recoverySchedulerDelay=recoverySchedulerDelay, recoverySchedulerInitialDelay=recoverySchedulerInitialDelay, recoveryExpirationOffset=1)";

        Assertions.assertEquals(expected, password.toString());
    }

    @Test
    public void whenPasswordHashCode() {

        AppConfig.Password password = password();

        Assertions.assertEquals(-1853700800, password.hashCode());
    }

    @Test
    public void whenPasswordEquals() {

        AppConfig.Password password1 = password();
        AppConfig.Password password2 = password();

        Assertions.assertEquals(password1, password2);
    }

    @Test
    public void whenPasswordNotEquals() {

        AppConfig.Password password = password();

        Assertions.assertNotEquals(password, new AppConfig.Password());
    }

    private AppConfig config() {

        AppConfig config = new AppConfig();
        config.setUrl("url");
        config.setEnableSeedData(true);
        config.setAdminUser(new AppConfig.AdminUser());
        config.setEmail(new AppConfig.Email());
        config.setPassword(new AppConfig.Password());

        return config;
    }

    private AppConfig.Email email() {

        AppConfig.Email email = new AppConfig.Email();
        email.setNoReplyAddress("noReplyAddress");
        email.setSupportAddress("supportAddress");
        email.setSchedulerDelay("schedulerDelay");
        email.setSchedulerInitialDelay("schedulerInitialDelay");
        email.setPagingSize(1);

        return email;
    }

    private AppConfig.AdminUser adminUser() {

        AppConfig.AdminUser adminUser = new AppConfig.AdminUser();
        adminUser.setUsername("username");
        adminUser.setPassword("password");

        return adminUser;
    }

    private AppConfig.Password password() {

        AppConfig.Password password = new AppConfig.Password();
        password.setRecoverySchedulerDelay("recoverySchedulerDelay");
        password.setRecoverySchedulerInitialDelay("recoverySchedulerInitialDelay");
        password.setRecoveryExpirationOffset(1L);

        return password;
    }
}
