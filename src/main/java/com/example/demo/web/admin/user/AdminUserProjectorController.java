package com.example.demo.web.admin.user;

import com.example.demo.web.admin.user.form.AdminUserFilter;
import com.example.demo.web.admin.user.service.model.FetchAdminUserTableQuery;
import com.example.demo.web.admin.user.service.model.FetchAdminUserTableResponse;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.ExecutionException;


@Controller
@RequestMapping("/admin/users")
@RequiredArgsConstructor
public class AdminUserProjectorController {

    private final QueryGateway queryGateway;

    @GetMapping("")
    public String getDefault() {

        return "admin/user/view-default";
    }

    @GetMapping("/table")
    public String getUsersTable(Model model, @ModelAttribute("filter") AdminUserFilter filter, @PageableDefault(size = 20) Pageable pageable) throws ExecutionException, InterruptedException {

        FetchAdminUserTableQuery query = FetchAdminUserTableQuery.builder()
                .email(filter.getEmail())
                .states(filter.getSelectedStates())
                .types(filter.getSelectedTypes())
                .pageable(pageable)
                .build();

        FetchAdminUserTableResponse response = queryGateway.query(query, FetchAdminUserTableResponse.class).get();

        model.addAttribute("pageable", response.getPage());

        return "admin/user/partial/partial-table";
    }
}
