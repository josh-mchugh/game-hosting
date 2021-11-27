package com.example.demo.web.registration.service;

import com.example.demo.web.registration.service.model.ExistsUserByEmailQuery;
import com.example.demo.web.registration.service.model.ExistsUserByEmailResponse;

public interface IRegistrationProjectorService {

    ExistsUserByEmailResponse existsByEmail(ExistsUserByEmailQuery query);
}
