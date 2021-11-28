package com.example.demo.awx.playbook.feign;

import java.util.List;

public interface PlaybookFeignService {

    List<String> getPlaybooks(Long projectId);
}
