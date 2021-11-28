package com.example.demo.web.admin.game;

import com.example.demo.framework.web.ModalResponse;
import com.example.demo.framework.web.Select2Response;
import com.example.demo.game.aggregate.command.GameServerCreateCommand;
import com.example.demo.util.MessageService;
import com.example.demo.web.admin.game.form.AdminGameServerCreateForm;
import com.example.demo.web.admin.game.form.AdminGameServerSelect2Request;
import com.example.demo.web.admin.game.service.AdminGameServerService;
import com.example.demo.web.admin.game.service.model.ExistsGameServerByNameQuery;
import com.example.demo.web.admin.game.service.model.ExistsGameServerByNameResponse;
import com.example.demo.web.admin.game.service.model.FetchAdminGameServerFlavorsQuery;
import com.example.demo.web.admin.game.service.model.FetchAdminGameServerFlavorsResponse;
import com.example.demo.web.admin.game.service.model.FetchAdminGameServerGamesQuery;
import com.example.demo.web.admin.game.service.model.FetchAdminGameServerGamesResponse;
import com.example.demo.web.admin.game.service.model.FetchAdminGameServerImagesQuery;
import com.example.demo.web.admin.game.service.model.FetchAdminGameServerImagesResponse;
import com.example.demo.web.admin.game.service.model.FetchAdminGameServerRegionsQuery;
import com.example.demo.web.admin.game.service.model.FetchAdminGameServerRegionsResponse;
import com.example.demo.web.admin.game.service.model.FetchAdminGameServerTableQuery;
import com.example.demo.web.admin.game.service.model.FetchAdminGameServerTableResponse;
import com.example.demo.web.admin.game.service.projection.AdminGameServerFlavorProjection;
import com.example.demo.web.admin.game.service.projection.AdminGameServerGameProjection;
import com.example.demo.web.admin.game.service.projection.AdminGameServerImageProjection;
import com.example.demo.web.admin.game.service.projection.AdminGameServerRegionProjection;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Controller
@RequestMapping("/admin/game-servers")
@RequiredArgsConstructor
public class AdminGameServerProjectorController {

    private final AdminGameServerService service;
    private final MessageService messageService;
    private final CommandGateway commandGateway;

    @GetMapping("")
    public String getDefault() {

        return "admin/game/view-default";
    }

    @GetMapping("/table")
    public String getTable(Model model, @PageableDefault(size = 20) Pageable pageable) throws ExecutionException, InterruptedException {

        FetchAdminGameServerTableQuery query = new FetchAdminGameServerTableQuery(pageable);
        FetchAdminGameServerTableResponse response = service.getTable(query);

        model.addAttribute("pageable", response.getGameServers());

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

        FetchAdminGameServerGamesResponse response = service.getGames(new FetchAdminGameServerGamesQuery());

        return new ResponseEntity<>(new Select2Response<>(response.getGames()), HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping("/regions")
    public ResponseEntity<Select2Response<AdminGameServerRegionProjection>> getRegions() throws ExecutionException, InterruptedException {

        FetchAdminGameServerRegionsResponse response = service.getRegions(new FetchAdminGameServerRegionsQuery());

        return new ResponseEntity<>(new Select2Response<>(response.getRegions()), HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping("/flavors")
    public ResponseEntity<Select2Response<AdminGameServerFlavorProjection>> getFlavors(@Valid AdminGameServerSelect2Request request) throws ExecutionException, InterruptedException {

        FetchAdminGameServerFlavorsQuery query = new FetchAdminGameServerFlavorsQuery(request.getSearch(), request.getRegionId());
        FetchAdminGameServerFlavorsResponse response = service.getFlavors(query);

        return new ResponseEntity<>(new Select2Response<>(response.getFlavors()), HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping("/images")
    public ResponseEntity<Select2Response<AdminGameServerImageProjection>> getImages(@Valid AdminGameServerSelect2Request request) throws ExecutionException, InterruptedException {

        FetchAdminGameServerImagesQuery query = new FetchAdminGameServerImagesQuery(request.getSearch(), request.getRegionId());
        FetchAdminGameServerImagesResponse response = service.getImages(query);

        return new ResponseEntity<>(new Select2Response<>(response.getImages()), HttpStatus.OK);
    }

    @PostMapping("/create")
    public String postCreateModal(Model model, @Valid @ModelAttribute("form") AdminGameServerCreateForm form, BindingResult result) throws ExecutionException, InterruptedException {

        if(StringUtils.isNotBlank(form.getName())) {

            ExistsGameServerByNameQuery query = new ExistsGameServerByNameQuery(form.getName());
            ExistsGameServerByNameResponse response = service.existsByName(query);

            if (response.exists()) {

                result.rejectValue("name", "error.name.exists", "Name already exists");
            }
        }

        if(result.hasErrors()) {

            return "admin/game/partial/modal-game-server-create";
        }

        GameServerCreateCommand command = GameServerCreateCommand.builder()
                .id(UUID.randomUUID())
                .name(form.getName())
                .description(form.getDescription())
                .status(form.getSelectedStatus())
                .gameId(UUID.fromString(form.getGameId()))
                .regionId(UUID.fromString(form.getRegionId()))
                .flavorId(UUID.fromString(form.getFlavorId()))
                .imageId(UUID.fromString(form.getImageId()))
                .build();

        commandGateway.send(command);

        return new ModalResponse(model)
                .toast(ModalResponse.Type.SUCCESS, messageService.getMessage("message.admin.game.server.create.modal.success"))
                .event(ModalResponse.RELOAD, "gameServersTable")
                .build();
    }
}
