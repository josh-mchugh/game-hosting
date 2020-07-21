package com.example.demo.ovh.model;

import lombok.Data;

import java.util.List;

@Data
public class SshKey {

    private String id;
    private String name;
    private String publicKey;
    private List<String> regions;
}
