package com.example.demo.web.admin.game.projection.service;

import com.example.demo.framework.web.Select2Response;
import com.example.demo.game.projection.model.AdminGameServerGameProjection;
import com.example.demo.ovh.flavor.projection.model.AdminGameServerFlavorProjection;
import com.example.demo.ovh.image.projection.model.AdminGameServerImageProjection;
import com.example.demo.ovh.region.projection.model.AdminGameServerRegionProjection;
import com.example.demo.web.admin.game.projection.model.AdminGameServerPageRequest;
import com.example.demo.web.admin.game.projection.service.projection.AdminGameServerTableProjection;
import org.springframework.data.domain.Page;

public interface IAdminGameServerProjectorService {

    Select2Response<AdminGameServerGameProjection> getGames();
    Select2Response<AdminGameServerRegionProjection> getRegions();
    Select2Response<AdminGameServerFlavorProjection> getFlavors(String search, String regionId);
    Select2Response<AdminGameServerImageProjection> getImages(String search, String regionId);
    Page<AdminGameServerTableProjection> getPage(AdminGameServerPageRequest request);
}
