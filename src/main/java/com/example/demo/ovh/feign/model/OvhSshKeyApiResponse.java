package com.example.demo.ovh.feign.model;

import lombok.Data;

import java.util.List;

@Data
public class OvhSshKeyApiResponse {

    private String id;
    private String name;
    private String publicKey;
    private List<String> regions;
}
