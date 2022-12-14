package com.example.demo.web.admin.user.service.model;

import com.example.demo.user.entity.UserState;
import com.example.demo.user.entity.UserType;
import lombok.Builder;
import lombok.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collection;

@Value
@Builder(builderClassName = "Builder")
public class FetchAdminUserTableQuery {

    String email;
    Collection<UserState> states;
    Collection<UserType> types;

    @lombok.Builder.Default
    Pageable pageable = PageRequest.of(0, 20);
}
