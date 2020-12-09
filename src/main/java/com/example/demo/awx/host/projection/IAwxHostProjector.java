package com.example.demo.awx.host.projection;

import com.example.demo.awx.host.projection.model.AwxHostAwxIdProjection;
import com.example.demo.awx.host.projection.model.AwxHostAwxIdQuery;

public interface IAwxHostProjector {

    boolean existsAny();

    AwxHostAwxIdProjection getHostIdProjection(AwxHostAwxIdQuery query);
}
