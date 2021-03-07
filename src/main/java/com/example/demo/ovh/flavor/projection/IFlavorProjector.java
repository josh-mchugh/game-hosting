package com.example.demo.ovh.flavor.projection;

import com.example.demo.ovh.flavor.entity.model.Flavor;

public interface IFlavorProjector {

    Flavor fetchFlavorByOvhId(String ovhId);
}
