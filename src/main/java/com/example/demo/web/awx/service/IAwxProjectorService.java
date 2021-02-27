package com.example.demo.web.awx.service;

import com.example.demo.web.awx.service.model.ExistsAnyPlaybooksQuery;
import com.example.demo.web.awx.service.model.ExistsAnyPlaybooksResponse;
import com.example.demo.web.awx.service.model.FetchProjectByAwxIdQuery;
import com.example.demo.web.awx.service.model.FetchProjectByAwxIdResponse;

public interface IAwxProjectorService {

    ExistsAnyPlaybooksResponse existsAnyPlaybooks(ExistsAnyPlaybooksQuery query);

    FetchProjectByAwxIdResponse getProjectByAwxId(FetchProjectByAwxIdQuery query);
}
