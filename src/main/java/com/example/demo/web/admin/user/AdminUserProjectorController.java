package com.example.demo.web.admin.user;

import com.example.demo.web.admin.user.form.AdminUserFilter;
import com.example.demo.web.admin.user.service.IAdminUserProjectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/admin/users")
@RequiredArgsConstructor
public class AdminUserProjectorController {

    private final IAdminUserProjectorService adminUserProjectorService;

    @GetMapping("")
    public String getDefault() {

        return "admin/user/view-default";
    }

    @GetMapping("/table")
    public String getUsersTable(Model model, @ModelAttribute("filter") AdminUserFilter filter, @PageableDefault(size = 20) Pageable pageable) {

        model.addAttribute("pageable", adminUserProjectorService.fetchAdminUsersPage(filter, pageable));

        return "admin/user/partial/partial-table";
    }
}
