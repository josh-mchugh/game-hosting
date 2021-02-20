package com.example.demo.web.project.projection;

import com.example.demo.web.project.projection.service.model.FetchProjectDetailsQuery;
import com.example.demo.web.project.projection.service.model.FetchProjectDetailsResponse;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.ExecutionException;

@Controller
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectProjectorController {

    private final QueryGateway queryGateway;

    @GetMapping("/{id}")
    public String getDefault(Model model, @PathVariable("id") String id) throws ExecutionException, InterruptedException {

        FetchProjectDetailsQuery query = new FetchProjectDetailsQuery(id);
        FetchProjectDetailsResponse response = queryGateway.query(query, FetchProjectDetailsResponse.class).get();

        model.addAttribute("details", response);

        return "project/view-default";
    }
}
