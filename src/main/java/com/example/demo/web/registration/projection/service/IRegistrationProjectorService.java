package com.example.demo.web.registration.projection.service;

import com.example.demo.web.registration.projection.service.model.ExistsUserByEmailQuery;
import com.example.demo.web.registration.projection.service.model.ExistsUserByEmailResponse;

public interface IRegistrationProjectorService {

    ExistsUserByEmailResponse existsByEmail(ExistsUserByEmailQuery query);
}
