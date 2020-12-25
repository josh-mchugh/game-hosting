package com.example.demo.ovh.instance.projection;

import com.example.demo.ovh.instance.projection.model.FetchInstanceByOvhIdsProjection;
import com.example.demo.ovh.instance.projection.model.FetchInstancesByOvhIdsQuery;

public interface IInstanceProjector {

    FetchInstanceByOvhIdsProjection fetchInstancesByIds(FetchInstancesByOvhIdsQuery query);
}
