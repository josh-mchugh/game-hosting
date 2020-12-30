package com.example.demo.web.test;

import com.example.demo.ovh.instance.feign.IInstanceGroupFeignService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

    private final IInstanceGroupFeignService instanceGroupFeignService;

    @GetMapping("/ovh/project/groups")
    public ResponseEntity<?> getGroups() {

        return new ResponseEntity<>(instanceGroupFeignService.getInstanceGroups(), HttpStatus.OK);
    }

    @GetMapping("/ovh/project/group/delete")
    public ResponseEntity<?> deleteGroup() {

        instanceGroupFeignService.getInstanceGroups().forEach(api -> instanceGroupFeignService.deleteInstanceGroupById(api.getId()));

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
