package com.example.demo.web.admin.game.projection;

import com.example.demo.framework.web.Select2Response;
import com.example.demo.game.projection.model.AdminGameServerGameProjection;
import com.example.demo.ovh.flavor.projection.model.AdminGameServerFlavorProjection;
import com.example.demo.ovh.image.projection.model.AdminGameServerImageProjection;
import com.example.demo.ovh.region.projection.model.AdminGameServerRegionProjection;
import com.example.demo.web.admin.game.form.AdminGameServerCreateForm;
import com.example.demo.web.admin.game.projection.model.AdminGameServerPageRequest;
import com.example.demo.web.admin.game.projection.model.AdminGameServerSelect2Request;
import com.example.demo.web.admin.game.projection.service.IAdminGameServerProjectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin/game-servers")
@RequiredArgsConstructor
public class AdminGameServerProjectorController {

    private final IAdminGameServerProjectorService gameServerService;

    @GetMapping("")
    public String getDefault() {

        return "admin/game/view-default";
    }

    @GetMapping("/table")
    public String getTable(Model model, @PageableDefault(size = 20) Pageable pageable) {

        model.addAttribute("pageable", gameServerService.getPage(new AdminGameServerPageRequest(pageable)));

        return "admin/game/partial/partial-table";
    }

    @GetMapping("/create")
    public String getCreateModal(Model model) {

        model.addAttribute("form", new AdminGameServerCreateForm());

        return "admin/game/partial/modal-game-server-create";
    }

    @ResponseBody
    @GetMapping("/games")
    public ResponseEntity<Select2Response<AdminGameServerGameProjection>> getGames() {

        return new ResponseEntity<>(gameServerService.getGames(), HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping("/regions")
    public ResponseEntity<Select2Response<AdminGameServerRegionProjection>> getRegions() {

        return new ResponseEntity<>(gameServerService.getRegions(), HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping("/flavors")
    public ResponseEntity<Select2Response<AdminGameServerFlavorProjection>> getFlavors(@Valid AdminGameServerSelect2Request request) {

        return new ResponseEntity<>(gameServerService.getFlavors(request.getSearch(), request.getRegionId()), HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping("/images")
    public ResponseEntity<Select2Response<AdminGameServerImageProjection>> getImages(@Valid AdminGameServerSelect2Request request) {

        return new ResponseEntity<>(gameServerService.getImages(request.getSearch(), request.getRegionId()), HttpStatus.OK);
    }
}
