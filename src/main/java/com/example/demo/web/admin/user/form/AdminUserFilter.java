package com.example.demo.web.admin.user.form;

import com.example.demo.user.entity.UserState;
import com.example.demo.user.entity.UserType;
import com.google.common.collect.Lists;
import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

@Data
public class AdminUserFilter {

    private String email;
    private List<UserState> selectedStates;
    private List<UserType> selectedTypes;
    private List<UserState> allStates = Lists.newArrayList(UserState.values());
    private List<UserType> allTypes = Lists.newArrayList(UserType.values());

    public boolean isStateChecked(UserState state) {

        if(CollectionUtils.isNotEmpty(selectedStates)) {

            return selectedStates.contains(state);
        }

        return false;
    }

    public boolean isTypeChecked(UserType type) {

        if(CollectionUtils.isNotEmpty(selectedTypes)) {

            return selectedTypes.contains(type);
        }

        return false;
    }
}
