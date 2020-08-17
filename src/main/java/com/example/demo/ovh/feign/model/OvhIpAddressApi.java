package com.example.demo.ovh.feign.model;

import lombok.Data;

@Data
public class OvhIpAddressApi {

    private String gatewayIp;
    private String ip;
    private String networkId;
    private String type;
    private Integer version;
}
