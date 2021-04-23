package com.example.demo.web.project;

import com.example.demo.web.project.form.ProjectCreateBillingForm;
import com.example.demo.web.project.form.ProjectCreateForm;
import com.example.demo.web.project.form.ProjectCreateRegionForm;
import com.example.demo.web.project.form.ProjectCreateServerForm;
import com.example.demo.web.project.service.IProjectCreateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectCreateController {

    private final IProjectCreateService service;

    @GetMapping("/create")
    public String getCreate(Model model) {

        if (!model.containsAttribute("form")) {

            model.addAttribute("form", new ProjectCreateForm());
        }

        return "project/create/view-create";
    }

    @PostMapping("/create")
    public String postCreate(Model model, RedirectAttributes redirectAttributes, @Valid @ModelAttribute("form") ProjectCreateForm form, BindingResult results) {

        if(results.hasErrors()) {

            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.form", results);
            redirectAttributes.addFlashAttribute("form", form);

            return "redirect:/project/create";
        }

        return "redirect:/project/1/create/region";
    }

    @GetMapping("/{id}/create/region")
    public String getCreateRegion(Model model, @PathVariable("id") String id) {

        if (!model.containsAttribute("form")) {

            model.addAttribute("form", new ProjectCreateRegionForm());
        }

        return "project/create/view-region";
    }

    @PostMapping("/{id}/create/region")
    public String getPostRegion(Model model, @PathVariable("id") String id, RedirectAttributes redirectAttributes, @Valid @ModelAttribute("form") ProjectCreateRegionForm form, BindingResult results) {

        if(results.hasErrors()) {

            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.form", results);
            redirectAttributes.addFlashAttribute("form", form);

            return String.format("redirect:/project/%s/create/region", id);
        }

        return String.format("redirect:/project/%s/create/server", id);
    }

    @GetMapping("/{id}/create/server")
    public String getCreateServer(Model model, @PathVariable("id") String id) {

        if (!model.containsAttribute("form")) {

            model.addAttribute("form", new ProjectCreateServerForm());
        }

        return "project/create/view-server";
    }

    @PostMapping("/{id}/create/server")
    public String postCreateServer(Model model, @PathVariable("id") String id, RedirectAttributes redirectAttributes, @Valid @ModelAttribute("form") ProjectCreateServerForm form, BindingResult results) {

        if(results.hasErrors()) {

            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.form", results);
            redirectAttributes.addFlashAttribute("form", form);

            return String.format("redirect:/project/%s/create/server", id);
        }

        return String.format("redirect:/project/%s/create/billing", id);
    }

    @GetMapping("/{id}/create/billing")
    public String getCreateBilling(Model model, @PathVariable("id") String id) {

        if (!model.containsAttribute("form")) {

            model.addAttribute("form", new ProjectCreateBillingForm());
        }

        return "project/create/view-billing";
    }

    @PostMapping("/{id}/create/billing")
    public String postCreateBilling(Model model, @PathVariable("id") String id, RedirectAttributes redirectAttributes, @Valid @ModelAttribute("form")ProjectCreateBillingForm form, BindingResult results) {

        if(results.hasErrors()) {

            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.form", results);
            redirectAttributes.addFlashAttribute("form", form);

            return String.format("redirect:/project/%s/create/billing", id);
        }

        return String.format("redirect:/project/%s", id);
    }
}
