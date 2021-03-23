package com.example.demo.awx.host.projection;

import com.example.demo.awx.host.projection.model.AwxHostAwxIdProjection;
import com.example.demo.awx.host.projection.model.AwxHostAwxIdQuery;

public interface IAwxHostProjector {

    AwxHostAwxIdProjection getHostIdProjection(AwxHostAwxIdQuery query);
}
