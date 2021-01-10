package com.example.demo.web.admin.user.form;

import com.example.demo.user.entity.UserState;
import com.example.demo.user.entity.UserType;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

public class AdminUserFilterTest {

    @Test
    public void whenFilterHasEmailThenReturnEmail() {

        AdminUserFilter filter = new AdminUserFilter();
        filter.setEmail("email");

        Assertions.assertEquals("email", filter.getEmail());
    }

    @Test
    public void whenFilterHasSelectedStatesThenReturnSelectedStates() {

        AdminUserFilter filter = new AdminUserFilter();
        filter.setSelectedStates(Collections.singletonList(UserState.ACTIVE));

        Assertions.assertEquals(Collections.singletonList(UserState.ACTIVE), filter.getSelectedStates());
    }

    @Test
    public void whenFilterHasSelectedTypeThenReturnSelectedType() {

        AdminUserFilter filter = new AdminUserFilter();
        filter.setSelectedTypes(Collections.singletonList(UserType.ADMIN));

        Assertions.assertEquals(Collections.singletonList(UserType.ADMIN), filter.getSelectedTypes());
    }

    @Test
    public void whenFilterHasAllStatesThenReturnAllStates() {

        Assertions.assertEquals(Lists.newArrayList(UserState.values()), new AdminUserFilter().getAllStates());
    }

    @Test
    public void whenFilterHasAllTypesThenReturnAllTypes() {

        Assertions.assertEquals(Lists.newArrayList(UserType.values()), new AdminUserFilter().getAllTypes());
    }

    @Test
    public void whenIsStateCheckedHasStateThenReturnTrue() {

        AdminUserFilter filter = new AdminUserFilter();
        filter.setSelectedStates(Collections.singletonList(UserState.ACTIVE));

        Assertions.assertTrue(filter.isStateChecked(UserState.ACTIVE));
    }

    @Test
    public void whenIsStateCheckedDoesNotHaveStateThenReturnFalse() {

        AdminUserFilter filter = new AdminUserFilter();
        filter.setSelectedStates(Collections.singletonList(UserState.ACTIVE));

        Assertions.assertFalse(filter.isStateChecked(null));
    }

    @Test
    public void whenIsStateCheckedAndNoneSelectedThenReturnFalse() {

        AdminUserFilter filter = new AdminUserFilter();

        Assertions.assertFalse(filter.isStateChecked(UserState.ACTIVE));
    }

    @Test
    public void whenIsTypeCheckedHasTypeThenReturnTrue() {

        AdminUserFilter filter = new AdminUserFilter();
        filter.setSelectedTypes(Collections.singletonList(UserType.ADMIN));

        Assertions.assertTrue(filter.isTypeChecked(UserType.ADMIN));
    }

    @Test
    public void whenIsTypeCheckedDoesNotHaveTypeThenReturnFalse() {

        AdminUserFilter filter = new AdminUserFilter();
        filter.setSelectedTypes(Collections.singletonList(UserType.ADMIN));

        Assertions.assertFalse(filter.isTypeChecked(UserType.REGULAR));
    }

    @Test
    public void whenIsTypeCheckedAndNoneSelectedThenReturnFalse() {

        AdminUserFilter filter = new AdminUserFilter();

        Assertions.assertFalse(filter.isTypeChecked(UserType.ADMIN));
    }

    @Test
    public void whenFilterToString() {

        AdminUserFilter filter = filter();

        String expected = "AdminUserFilter(email=email, selectedStates=[ACTIVE], selectedTypes=[ADMIN], allStates=[ACTIVE], allTypes=[ADMIN, REGULAR])";

        Assertions.assertEquals(expected, filter.toString());
    }

    @Test
    public void whenFilterHashCode() {

        AdminUserFilter filter = new AdminUserFilter();
        filter.setEmail("email");
        filter.setAllStates(null);
        filter.setAllStates(null);

        Assertions.assertNotEquals(1, filter.hashCode());
    }

    @Test
    public void whenFilterEquals() {

        AdminUserFilter filter1 = filter();
        AdminUserFilter filter2 = filter();

        Assertions.assertEquals(filter1, filter2);
    }

    @Test
    public void whenFilterNotEquals() {

        AdminUserFilter filter = filter();

        Assertions.assertNotEquals(filter, new AdminUserFilter());
    }

    private AdminUserFilter filter() {

        AdminUserFilter filter = new AdminUserFilter();
        filter.setEmail("email");
        filter.setSelectedStates(Collections.singletonList(UserState.ACTIVE));
        filter.setSelectedTypes(Collections.singletonList(UserType.ADMIN));

        return filter;
    }
}
