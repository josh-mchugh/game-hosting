package com.example.demo.web.project.projection.service;

import com.example.demo.ovh.instance.projection.IInstanceProjector;
import com.example.demo.ovh.instance.projection.model.FetchInstanceDetailsByProjectIdProjection;
import com.example.demo.ovh.instance.projection.model.FetchInstanceDetailsByProjectIdQuery;
import com.example.demo.project.projection.IProjectProjector;
import com.example.demo.project.projection.model.FetchProjectDetailsByIdQuery;
import com.example.demo.project.projection.model.FetchProjectDetailsByIdProjection;
import com.example.demo.web.project.projection.service.model.ProjectDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProjectProjectorService implements IProjectProjectorService {

    private final IProjectProjector projectProjector;
    private final IInstanceProjector instanceProjector;

    @Override
    public ProjectDetails getProjectDetails(String id) {

        FetchProjectDetailsByIdProjection projectDetails = projectDetailsProjection(id);
        FetchInstanceDetailsByProjectIdProjection instance = instanceDetailsProjection(id);

        return ProjectDetails.builder()
                .name(projectDetails.getName())
                .gameType(projectDetails.getGameType())
                .instanceId(instance.getOvhId())
                .instanceStatus(instance.getInstanceStatus())
                .ip4Address(instance.getIpAddress())
                .build();
    }

    private FetchProjectDetailsByIdProjection projectDetailsProjection(String id) {

        FetchProjectDetailsByIdQuery query = new FetchProjectDetailsByIdQuery(id);

        return projectProjector.fetchProjectDetails(query);
    }

    private FetchInstanceDetailsByProjectIdProjection instanceDetailsProjection(String id) {

        FetchInstanceDetailsByProjectIdQuery query = new FetchInstanceDetailsByProjectIdQuery(id);

        return instanceProjector.fetchInstanceDetails(query);
    }
}
