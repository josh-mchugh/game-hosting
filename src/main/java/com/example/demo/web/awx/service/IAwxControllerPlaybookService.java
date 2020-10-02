package com.example.demo.web.awx.service;

import com.example.demo.web.awx.service.model.PlaybookCreateRequest;
import com.google.common.collect.ImmutableList;

public interface IAwxControllerPlaybookService {

    ImmutableList<Object> handleCreatePlaybooks(PlaybookCreateRequest request);
}
