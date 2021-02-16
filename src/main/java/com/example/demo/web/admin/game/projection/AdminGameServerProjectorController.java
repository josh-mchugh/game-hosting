package com.example.demo.web.admin.game.projection;

import com.example.demo.framework.web.Select2Response;
import com.example.demo.web.admin.game.projection.service.projection.AdminGameServerFlavorProjection;
import com.example.demo.ovh.image.projection.model.AdminGameServerImageProjection;
import com.example.demo.web.admin.game.projection.service.model.FetchAdminGameServerFlavorsQuery;
import com.example.demo.web.admin.game.projection.service.model.FetchAdminGameServerFlavorsResponse;
import com.example.demo.web.admin.game.projection.service.model.FetchAdminGameServerRegionsQuery;
import com.example.demo.web.admin.game.projection.service.model.FetchAdminGameServerRegionsResponse;
import com.example.demo.web.admin.game.projection.service.projection.AdminGameServerRegionProjection;
import com.example.demo.web.admin.game.form.AdminGameServerCreateForm;
import com.example.demo.web.admin.game.projection.model.AdminGameServerPageRequest;
import com.example.demo.web.admin.game.projection.model.AdminGameServerSelect2Request;
import com.example.demo.web.admin.game.projection.service.IAdminGameServerProjectorService;
import com.example.demo.web.admin.game.projection.service.model.FetchAdminGameServerGamesQuery;
import com.example.demo.web.admin.game.projection.service.model.FetchAdminGameServerGamesResponse;
import com.example.demo.web.admin.game.projection.service.projection.AdminGameServerGameProjection;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryGateway;
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
import java.util.concurrent.ExecutionException;

@Controller
@RequestMapping("/admin/game-servers")
@RequiredArgsConstructor
public class AdminGameServerProjectorController {

    private final IAdminGameServerProjectorService gameServerService;
    private final QueryGateway queryGateway;

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
    public ResponseEntity<Select2Response<AdminGameServerGameProjection>> getGames() throws ExecutionException, InterruptedException {

        FetchAdminGameServerGamesResponse response = queryGateway.query(new FetchAdminGameServerGamesQuery(), FetchAdminGameServerGamesResponse.class).get();

        return new ResponseEntity<>(new Select2Response<>(response.getGames()), HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping("/regions")
    public ResponseEntity<Select2Response<AdminGameServerRegionProjection>> getRegions() throws ExecutionException, InterruptedException {

        FetchAdminGameServerRegionsResponse response = queryGateway.query(new FetchAdminGameServerRegionsQuery(), FetchAdminGameServerRegionsResponse.class).get();

        return new ResponseEntity<>(new Select2Response<>(response.getRegions()), HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping("/flavors")
    public ResponseEntity<Select2Response<AdminGameServerFlavorProjection>> getFlavors(@Valid AdminGameServerSelect2Request request) throws ExecutionException, InterruptedException {

        FetchAdminGameServerFlavorsQuery query = new FetchAdminGameServerFlavorsQuery(request.getSearch(), request.getRegionId());
        FetchAdminGameServerFlavorsResponse response = queryGateway.query(query, FetchAdminGameServerFlavorsResponse.class).get();

        return new ResponseEntity<>(new Select2Response<>(response.getFlavors()), HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping("/images")
    public ResponseEntity<Select2Response<AdminGameServerImageProjection>> getImages(@Valid AdminGameServerSelect2Request request) {

        return new ResponseEntity<>(gameServerService.getImages(request.getSearch(), request.getRegionId()), HttpStatus.OK);
    }
}
