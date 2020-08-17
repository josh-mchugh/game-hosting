package com.example.demo.ovh.feign.common;

import lombok.Data;

import java.util.List;

@Data
public class SshKeyDetailApi {

    String id;
    String name;
    String publicKey;
    String fingerPrint;
    List<String> regions;
}
