package com.example.demo.awx.host.projection;

import com.example.demo.awx.host.projection.model.AwxHostHostIdProjection;
import com.example.demo.awx.host.projection.model.AwxHostHostIdQuery;

public interface IAwxHostProjector {

    boolean existsAny();

    AwxHostHostIdProjection getHostIdProjection(AwxHostHostIdQuery query);
}
