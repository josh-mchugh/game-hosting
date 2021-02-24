package com.example.demo.web.dashboard.form;

import com.example.demo.game.entity.GameType;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class DashboardProjectCreateFormTest {

    @Test
    public void whenFormHasNameThenReturnName() {

        DashboardProjectCreateForm form = new DashboardProjectCreateForm();
        form.setName("name");

        Assertions.assertEquals("name", form.getName());
    }

    @Test
    public void whenFormHasGameThenReturnName() {

        DashboardProjectCreateForm form = new DashboardProjectCreateForm();
        form.setGame(GameType.MINECRAFT_JAVA);

        Assertions.assertEquals(GameType.MINECRAFT_JAVA, form.getGame());
    }

    @Test
    public void whenFormHasRegionThenReturnRegion() {

        DashboardProjectCreateForm form = new DashboardProjectCreateForm();
        form.setRegion("region");

        Assertions.assertEquals("region", form.getRegion());
    }

    @Test
    public void whenFormHasServerThenReturnServer() {

        DashboardProjectCreateForm form = new DashboardProjectCreateForm();
        form.setServer("server");

        Assertions.assertEquals("server", form.getServer());
    }

    @Test
    public void whenFormHasGamesThenReturnGames() {

        Assertions.assertEquals(Lists.newArrayList(GameType.values()), new DashboardProjectCreateForm().getGames());
    }

    @Test
    public void whenFormHasRegionsThenReturnRegions() {

        Assertions.assertEquals(Collections.singletonList("US-EAST-VA-1"), new DashboardProjectCreateForm().getRegions());
    }

    @Test
    public void whenFormHasServersThenReturnServers() {

        Assertions.assertEquals(Arrays.asList("1cpu - 2gb ram", "2cpu - 4gb ram"), new DashboardProjectCreateForm().getServers());
    }

    @Test
    public void whenFormToString() {

        DashboardProjectCreateForm form = form();

        String expected = "DashboardProjectCreateForm(name=name, game=MINECRAFT_JAVA, region=region, server=server, games=[MINECRAFT_JAVA, MINECRAFT_BEDROCK], regions=[US-EAST-VA-1], servers=[1cpu - 2gb ram, 2cpu - 4gb ram])";

        Assertions.assertEquals(expected, form.toString());
    }

    @Test
    public void whenFormHashCode() {

        DashboardProjectCreateForm form = new DashboardProjectCreateForm();
        form.setName("name");
        form.setRegion("region");
        form.setServer("server");
        form.setGames(new ArrayList<>());

        Assertions.assertEquals(-218759460, form.hashCode());
    }

    @Test
    public void whenFormEquals() {

        DashboardProjectCreateForm form1 = form();
        DashboardProjectCreateForm form2 = form();

        Assertions.assertEquals(form1, form2);
    }

    @Test
    public void whenFormNotEquals() {

        DashboardProjectCreateForm form = form();

        Assertions.assertNotEquals(form, new DashboardProjectCreateForm());
    }

    private DashboardProjectCreateForm form() {

        DashboardProjectCreateForm form = new DashboardProjectCreateForm();
        form.setName("name");
        form.setGame(GameType.MINECRAFT_JAVA);
        form.setRegion("region");
        form.setServer("server");

        return form;
    }
}
