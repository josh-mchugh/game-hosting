package com.example.demo.web.admin.ovh;

import com.example.demo.web.admin.ovh.model.InstanceGroupStatistic;
import com.example.demo.web.admin.ovh.service.IAdminOvhUtilsProjectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/admin/ovh-utils")
@RequiredArgsConstructor
public class AdminOvhUtilsController {

    private final IAdminOvhUtilsProjectorService service;

    @GetMapping("")
    public String getDefault() {

        return "admin/ovh/view-default";
    }

    @ResponseBody
    @GetMapping("/instance-group/refresh")
    public ResponseEntity<InstanceGroupStatistic> instanceGroupRefresh() {

        return new ResponseEntity<>(service.getInstanceGroupStatistics(), HttpStatus.OK);
    }

    @ResponseBody
    @PostMapping("/instance-group/delete")
    public ResponseEntity<InstanceGroupStatistic> deleteInstanceGroups() {

        return new ResponseEntity<>(service.handleInstanceGroupDelete(), HttpStatus.OK);
    }
}
