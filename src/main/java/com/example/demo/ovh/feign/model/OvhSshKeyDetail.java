package com.example.demo.ovh.feign.model;

import lombok.Data;

import java.util.List;

@Data
public class OvhSshKeyDetail {

    String id;
    String name;
    String publicKey;
    String fingerPrint;
    List<String> regions;
}
